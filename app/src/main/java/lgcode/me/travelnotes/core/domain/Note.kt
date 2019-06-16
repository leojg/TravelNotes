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
    var images: MutableList<Uri>? = null
): Parcelable {

    constructor(title: String? = null, body: String, date: Date, images: MutableList<Uri>? = null):
            this(null, title, body, date, images)

}