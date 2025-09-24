package com.tatsing.possystemsubscription.base

import android.content.Context
import com.tatsing.possystemsubscription.BuildConfig
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object HttpClientProvider {
    fun getSecureOkHttpClient(context: Context): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an SSL socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager) // Use SSL
                .hostnameVerifier { _, _ -> true } // Ignore hostname verification (not recommended for production)
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("apikey",
                            BuildConfig.API_KEY_ANON)
                        .addHeader("Content-Type", "application/json")
                        .build()
                    chain.proceed(request)
                }
                .build()
        } catch (e: Exception) {
            throw RuntimeException("Failed to create SSL socket factory", e)
        }
    }
}
