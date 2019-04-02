package lgcode.me.travelnotes.features.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.FragmentNotesListBinding
import lgcode.me.travelnotes.features.main.MainActivity
import lgcode.me.travelnotes.features.note.NoteFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment: BaseFragment() {

    companion object {
        fun newInstance(): NotesListFragment = NotesListFragment()
    }

    private val viewModel by viewModel<NotesListViewModel>()
    private lateinit var notesListBinding: FragmentNotesListBinding
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notesListBinding = FragmentNotesListBinding.inflate(inflater, container, false)
        noteListAdapter = NoteListAdapter(this)
        notesListBinding.notesList.adapter = noteListAdapter

        notesListBinding.newNoteFab.setOnClickListener {
            goToCreateNoteFragment()
        }

        return notesListBinding.root
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
        viewModel.fetchNotes()
    }

    override fun onPause() {
        super.onPause()
        clearObservers()
    }

    fun setupObservers() {
        viewModel.notesListLiveData.observe(this, Observer {
            noteListAdapter.addItems(it.orEmpty())
        })
    }

    fun clearObservers() {
        viewModel.notesListLiveData.removeObservers(this)
    }

    fun goToCreateNoteFragment() {
        (activity as MainActivity).replaceFragment(NoteFragment.newInstance(type = NoteFragment.NoteFragmentType.CREATE))
    }

    fun goToViewNoteFragment(note: Note) {
        (activity as MainActivity).replaceFragment(NoteFragment.newInstance(
            type = NoteFragment.NoteFragmentType.VIEW,
            note = note))
    }
}