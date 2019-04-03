package lgcode.me.travelnotes.features.note

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.FragmentNoteBinding
import lgcode.me.travelnotes.features.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

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
    var noteFragmentType = NoteFragmentType.CREATE
    private lateinit var menu: Menu


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        noteBinding = FragmentNoteBinding.inflate(inflater, container, false)
        noteBinding.viewModel = viewModel

        setHasOptionsMenu(true)

        noteFragmentType =  NoteFragmentType.valueOf(arguments!!.getString(FRAGMENT_TYPE)!!)
        if (noteFragmentType != NoteFragmentType.CREATE) {
            viewModel.setNote(arguments!!.get(FRAGMENT_NOTE) as Note)
        }
        if (noteFragmentType == NoteFragmentType.VIEW) {
            startViewMode()
        }

        noteBinding.noteDateTextView.setOnClickListener{
            it.isEnabled = false
            val calendar = viewModel.noteCalendar.value!!
            DatePickerDialog(this.context!!, datePickerListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show()
        }

        return noteBinding.root
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
        viewModel.noteCalendar.observe(this, Observer { calendar ->
            updateDate(calendar.time)
        })
        viewModel.operationStatus.observe(this, Observer {
            if (it) {
                fragmentManager!!.popBackStack()
            }
        })
    }

    fun clearObservers() {
        viewModel.noteCalendar.removeObservers(this)
    }

    fun updateDate(date: Date) {
        val dateFormat = SimpleDateFormat("dd / MM / yy", Locale.getDefault())
        noteBinding.noteDateTextView.text = getString(R.string.note_date_label, dateFormat.format(date))

    }

    val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, day, month ->
        val calendar = viewModel.noteCalendar.value!!
        calendar.set(year, day, month)
        noteBinding.noteDateTextView.isEnabled = true
        updateDate(calendar.time)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_create_note, menu)
        menu?.let {
            when(noteFragmentType) {
                NoteFragmentType.VIEW -> {
                    val itemSave = menu.findItem(R.id.action_save_note)
                    val itemEdit = menu.findItem(R.id.action_edit_note)
                    itemSave.isVisible = false
                    itemEdit.isVisible = true
                }
            }
            this.menu = menu
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                R.id.action_save_note -> viewModel.saveNote()
                R.id.action_edit_note -> startEditMode()
                R.id.action_update_note -> viewModel.updateNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun startEditMode() {
        noteFragmentType = NoteFragmentType.EDIT
        noteBinding.noteDateTextView.isEnabled = true
        noteBinding.noteTitleEditText.isEnabled = true
        noteBinding.noteBodyEditText.isEnabled = true
        noteBinding.noteAddImagesButton.visibility = View.VISIBLE
        val itemEdit = menu.findItem(R.id.action_edit_note)
        val itemUpdate = menu.findItem(R.id.action_update_note)
        itemEdit.isVisible = false
        itemUpdate.isVisible = true

    }

    fun startViewMode() {
        noteBinding.noteDateTextView.isEnabled = false
        noteBinding.noteTitleEditText.isEnabled = false
        noteBinding.noteBodyEditText.isEnabled = false
        noteBinding.noteAddImagesButton.visibility = View.GONE
    }

}