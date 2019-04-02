package lgcode.me.travelnotes.features.noteslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.databinding.ItemNoteBinding
import java.text.DateFormat

class NoteListAdapter(var noteListFragment: NotesListFragment): RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    private val notesList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        return NoteListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_note, parent, false))
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        notesList[position].let { note ->
            holder.binding.noteSummaryTextview.text = if (note.title.isNullOrEmpty()) note.body else note.title
            holder.binding.noteDateTextview.text = DateFormat.getDateInstance().format(note.date)
            holder.itemView.setOnClickListener {
                noteListFragment.goToViewNoteFragment(note)
            }
        }
    }

    fun addItems(notes: List<Note>) {
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

    class NoteListViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)
}