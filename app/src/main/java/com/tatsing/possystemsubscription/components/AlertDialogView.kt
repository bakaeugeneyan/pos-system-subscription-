package com.tatsing.possystemsubscription.components

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.tatsing.possystemsubscription.ui.theme.POSSystemSubscriptionTheme

@Composable
fun AlertDialogView(
	onDismissRequest: () -> Unit,
	onConfirmation: () -> Unit,
	dialogTitle: String,
	dialogText: String,
	positiveBtnText: String,
	negativeBtnText: String,
	icon: ImageVector? = null,
//	buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
	positiveButtonColors: ButtonColors = ButtonDefaults.buttonColors(),
	negativeButtonColors: ButtonColors = ButtonDefaults.buttonColors()
) {
	AlertDialog(
		icon = {
			if (icon != null) {
				Icon(icon, contentDescription = "Example Icon")
			}
		},
		title = {
			Text(text = dialogTitle)
		},
		text = {
			Text(text = dialogText)
		},
		onDismissRequest = {
//			onDismissRequest()
		},
		confirmButton = {
			if (!positiveBtnText.isNullOrEmpty()) {
				Button(
					onClick = {
						onConfirmation()
					},
					colors = positiveButtonColors
				) {
					Text(positiveBtnText)
				}
			}
		},
		dismissButton = {
			Button(
				onClick = {
					onDismissRequest()
				},
				colors = negativeButtonColors
			) {
				Text(negativeBtnText)
			}
		}
	)
}

@Preview(
	uiMode = Configuration.UI_MODE_NIGHT_YES,
	device = Devices.DEFAULT,
)
@Preview(
	uiMode = Configuration.UI_MODE_NIGHT_NO,
	device = Devices.DEFAULT,
)
@Preview(
	uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL and Configuration.ORIENTATION_PORTRAIT,
	device = Devices.TABLET,
)
@Composable
private fun LoginOtpPhoneViewPreview() {
	POSSystemSubscriptionTheme {
		AlertDialogView(
			onDismissRequest = {
			},
			onConfirmation = {
			},
			dialogTitle = "Confirm Logout",
			dialogText = "Are your sure you want to logout?",
			positiveBtnText = "Yes",
			negativeBtnText = "No",
			icon = null
		)
	}
}