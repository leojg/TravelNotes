package lgcode.me.travelnotes.features.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.FragmentNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment: BaseFragment() {

    enum class NoteFragmentType {
        CREATE, EDIT, VIEW
    }

    companion object {

        const val FRAGMENT_NOTE = "FRAGMENT_NOTE"
        const val FRAGMENT_TYPE = "FRAGMENT_TYPE"

        fun newInstance(note: Note? = null, type: NoteFragmentType): NoteFragment {
            val bundle = Bundle()
            note?.let {
                bundle.putParcelable(FRAGMENT_NOTE, note)
            }
            bundle.putString(FRAGMENT_TYPE, type.name)
            val fragment = NoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel by viewModel<NoteViewModel>()
    private lateinit var noteBinding: FragmentNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        noteBinding = FragmentNoteBinding.inflate(inflater, container, false)
        return noteBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}