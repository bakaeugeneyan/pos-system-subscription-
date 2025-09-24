package com.tatsing.possystemsubscription.coverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatsing.possystemsubscription.data.entities.user.UserMetadataEntity

class MetaDataConverter {

    private val gson = Gson()

    // --- UserMetadataEntity ---
    @TypeConverter
    fun fromUserMetadataEntity(value: UserMetadataEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toUserMetadataEntity(value: String?): UserMetadataEntity? {
        return value?.let {
            val type = object : TypeToken<UserMetadataEntity>() {}.type
            gson.fromJson(it, type)
        }
    }
}
