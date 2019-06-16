package lgcode.me.travelnotes.core.domain

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Notes")
class NoteEntity (
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val title: String? = null,
    val body: String,
    val date: Date,
    var images: List<String>? = null
)