package me.lgcode.balance.core.repository.local.room

import android.net.Uri
import androidx.room.TypeConverter
import java.util.*

object Converters {

    @JvmStatic @TypeConverter fun toDate(value: Long?): Date? = if (value == null) null else Date(value)
    @JvmStatic @TypeConverter fun toLong(value: Date?): Long? = value?.time

    @JvmStatic @TypeConverter fun toString(value: Uri?): String? = value?.toString()
    @JvmStatic @TypeConverter fun fromUri(value: String?): Uri? = value.let { Uri.parse(value) }


}