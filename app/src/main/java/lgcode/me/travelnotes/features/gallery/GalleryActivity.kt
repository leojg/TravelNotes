package lgcode.me.travelnotes.features.gallery

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
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

class GalleryActivity: BaseActivity(), LoaderManager.LoaderCallbacks<Cursor> {

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
        LoaderManager.getInstance(this).initLoader(GALLERY_LOADER, null, this)

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "latest")
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val selection: String? = null
        val selectionArgs = arrayOf<String>()
        val sortOrder: String? = null
        return CursorLoader(applicationContext, uri, projection, selection, selectionArgs, sortOrder)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        data?.let {
            val imageList = ArrayList<Uri>(data.count)
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            while(it.moveToNext()) {
                val imageUri = Uri.parse(it.getString(columnIndex))
                imageList.add(imageUri)
            }
            galleryAdapter.addItems(imageList)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRoot(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragmentContainer() = -1
}