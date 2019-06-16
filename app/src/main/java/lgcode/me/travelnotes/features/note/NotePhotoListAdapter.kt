package lgcode.me.travelnotes.features.note

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.databinding.ItemPhotoBinding
import java.io.File

class NotePhotoListAdapter(val context: Context): RecyclerView.Adapter<NotePhotoListAdapter.NotePhotoViewHolder>() {

    private val photoList = ArrayList<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotePhotoViewHolder =
        NotePhotoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false))

    override fun onBindViewHolder(holder: NotePhotoViewHolder, position: Int) {
        val uri = photoList[position]
        Glide.with(context).load(uri).into(holder.binding.notePhoto)

        holder.binding.notePhotoRemoveButton.setOnClickListener {
            photoList.remove(uri)
            notifyItemRemoved(position)
        }
    }

    fun addItem(uri: Uri) {
        photoList.add(uri)
        notifyDataSetChanged()
    }

    fun addItems(uris: List<Uri>) {
        photoList.addAll(uris)
        notifyDataSetChanged()
    }

    override fun getItemCount() = photoList.size


    class NotePhotoViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

}