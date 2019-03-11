package lgcode.me.travelnotes.core.domain

import android.net.Uri
import java.util.*

data class Note(
    val text: String,
    val date: Date,
    var image: Uri?
)