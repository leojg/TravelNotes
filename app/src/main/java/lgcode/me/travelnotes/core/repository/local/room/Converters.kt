package lgcode.me.travelnotes.core.repository.local.room

import android.net.Uri
import androidx.room.TypeConverter
import java.util.*

object Converters {

    @JvmStatic @TypeConverter fun toDate(value: Long?): Date? = value?.let { Date(value) }
    @JvmStatic @TypeConverter fun toLong(value: Date?): Long? = value?.time

    @JvmStatic @TypeConverter fun toString(value: Uri?): String? = value?.toString()
    @JvmStatic @TypeConverter fun fromUri(value: String?): Uri? = value?.let { Uri.parse(value) }

    @JvmStatic @TypeConverter fun toString(value: List<String>?): String {
        var result = ""
        value?.forEach {
            result += "$it,"
        }
        result.removeSuffix(",")
        return result
    }

    @JvmStatic @TypeConverter fun toList(value: String?): List<String> {
        return value!!.split(",")
    }


}