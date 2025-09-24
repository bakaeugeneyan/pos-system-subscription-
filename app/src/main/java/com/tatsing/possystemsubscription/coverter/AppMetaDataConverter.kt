package com.tatsing.possystemsubscription.coverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatsing.possystemsubscription.data.entities.user.AppMetadataEntity

class AppMetaDataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAppMetadataEntity(value: AppMetadataEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toAppMetadataEntity(value: String?): AppMetadataEntity? {
        return value?.let {
            val type = object : TypeToken<AppMetadataEntity>() {}.type
            gson.fromJson(it, type)
        }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, type)
        }
    }
}
