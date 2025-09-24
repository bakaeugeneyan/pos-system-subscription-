package com.tatsing.possystemsubscription.coverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatsing.possystemsubscription.data.entities.user.IdentityDataEntity
import com.tatsing.possystemsubscription.data.entities.user.IdentityEntity


class IdentityConverter {

    private val gson = Gson()

    // ---- IdentityDataEntity ----
    @TypeConverter
    fun fromIdentityDataEntity(value: IdentityDataEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toIdentityDataEntity(value: String?): IdentityDataEntity? {
        return value?.let {
            val type = object : TypeToken<IdentityDataEntity>() {}.type
            gson.fromJson(it, type)
        }
    }

    // ---- IdentityEntity List ----
    @TypeConverter
    fun fromIdentityEntityList(value: List<IdentityEntity>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toIdentityEntityList(value: String?): List<IdentityEntity>? {
        return value?.let {
            val type = object : TypeToken<List<IdentityEntity>>() {}.type
            gson.fromJson(it, type)
        }
    }
}
