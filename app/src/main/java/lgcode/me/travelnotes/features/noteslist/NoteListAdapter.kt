package lgcode.me.travelnotes.features.noteslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.databinding.ItemNoteBinding

class NoteListAdapter(): RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    private val notesList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        return NoteListViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        notesList[position].let {
            with(holder) {
                bind()
            }
        }
    }

    fun addItems(notes: List<Note>) {
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

    class NoteListViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                executePendingBindings()
            }
        }
    }
}