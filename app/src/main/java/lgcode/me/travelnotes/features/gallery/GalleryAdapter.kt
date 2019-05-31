package lgcode.me.travelnotes.features.gallery

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.databinding.ItemGalleryBinding
import java.io.File

class GalleryAdapter(val context: Context): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private val imageList = ArrayList<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GalleryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gallery, parent, false))

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val uri = imageList[position]
        Picasso.get().load(File(uri.path)).into(holder.binding.galleryItemImageview)
    }

    fun addItems(imageList: List<Uri>) {
        this.imageList.clear()
        this.imageList.addAll(imageList)
        notifyDataSetChanged()
    }

    class GalleryViewHolder(val binding: ItemGalleryBinding): RecyclerView.ViewHolder(binding.root)

}