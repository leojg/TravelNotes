package lgcode.me.travelnotes.core.domain

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: Long? = null,
    val title: String? = null,
    val body: String,
    val date: Date,
    var image: Uri? = null
): Parcelable {

    constructor(title: String? = null, body: String, date: Date, image: Uri? = null):
            this(null, title, body, date, image)

}