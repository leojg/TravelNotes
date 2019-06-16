package lgcode.me.travelnotes.features.gallery

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.MediaStore
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.ui.BaseActivity
import lgcode.me.travelnotes.databinding.ActivityGalleryBinding

class GalleryActivity: BaseActivity() {

    companion object {
        const val GALLERY_LOADER = 101

        fun newIntent(context: Context) = Intent(context, GalleryActivity::class.java)
    }

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryBinding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        galleryBinding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        galleryAdapter = GalleryAdapter(this)
        galleryBinding.galleryList.layoutManager = GridLayoutManager(this, 3)
        galleryBinding.galleryList.adapter = galleryAdapter
        fetchGalleryImages()
    }

    fun fetchGalleryImages() {
        contentResolver?.let {
            val projection = arrayOf(MediaStore.Images.Media._ID)
            it.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null).use { cursor ->
                val uriList = ArrayList<Uri>(cursor!!.count)
                while (cursor.moveToNext()) {
                    val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))).build()
                    uriList.add(uri)
                }
                cursor.close()
                galleryAdapter.addItems(uriList)
            }
        }
    }


    override fun getRoot(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragmentContainer() = -1
}