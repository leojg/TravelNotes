package lgcode.me.travelnotes.core.util

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Environment
import java.io.File
import java.util.*

fun createImageFile(context: Context): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("JPEG_${timestamp}_",".jpg", storageDir)
}

fun addPhotoToGallery(context: Context, photoUri: Uri) {
    Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also {
        it.data = photoUri
        context.sendBroadcast(it)
    }
}