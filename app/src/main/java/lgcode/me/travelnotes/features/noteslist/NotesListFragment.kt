package lgcode.me.travelnotes.features.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.FragmentNotesListBinding

class NotesListFragment: BaseFragment() {

    companion object {
        fun newInstance(): NotesListFragment = NotesListFragment()
    }

    private lateinit var viewModel: NotesListViewModel
    private lateinit var notesListBinding: FragmentNotesListBinding
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notesListBinding = FragmentNotesListBinding.inflate(inflater, container, false)
        noteListAdapter = NoteListAdapter()
        return notesListBinding.root
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
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
}