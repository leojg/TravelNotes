package lgcode.me.travelnotes.core.domain

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Notes")
class NotesEntity (
    @PrimaryKey val uid: Int = 0,
    val text: String,
    val date: Date,
    var image: Uri?
)