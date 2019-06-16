package lgcode.me.travelnotes.features.note

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import lgcode.me.travelnotes.BuildConfig
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.core.util.addPhotoToGallery
import lgcode.me.travelnotes.core.util.createImageFile
import lgcode.me.travelnotes.databinding.FragmentNoteBinding
import lgcode.me.travelnotes.features.gallery.GalleryActivity
import lgcode.me.travelnotes.features.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class NoteFragment: BaseFragment() {

    enum class NoteFragmentType {
        CREATE, EDIT, VIEW
    }

    companion object {

        const val FRAGMENT_NOTE = "FRAGMENT_NOTE"
        const val FRAGMENT_TYPE = "FRAGMENT_TYPE"

        const val REQUEST_IMAGE_CAPTURE = 201

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
    private lateinit var notePhotoAdapter: NotePhotoListAdapter

    private lateinit var photoUri: Uri


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        noteBinding = FragmentNoteBinding.inflate(inflater, container, false)
        noteBinding.viewModel = viewModel

        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        noteBinding.noteDateTextView.setOnClickListener{
            it.isEnabled = false
            val calendar = viewModel.noteCalendar.value!!
            DatePickerDialog(this.context!!, datePickerListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show()
        }

        noteBinding.noteAddImagesButton.setOnClickListener {
            val dialog = NotePictureSourceDialogFragment()
            dialog.setTargetFragment(this, 0)
            dialog.show(fragmentManager!!, "photo_source")
        }

        notePhotoAdapter = NotePhotoListAdapter(this.context!!)
        noteBinding.notePhotosList.adapter = notePhotoAdapter

        noteFragmentType =  NoteFragmentType.valueOf(arguments!!.getString(FRAGMENT_TYPE)!!)
        if (noteFragmentType != NoteFragmentType.CREATE) {
            viewModel.setNote(arguments!!.get(FRAGMENT_NOTE) as Note)

            viewModel.notePhotoUris?.let {
                notePhotoAdapter.addItems(it)
            }
        }
        if (noteFragmentType == NoteFragmentType.VIEW) {
            startViewMode()
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

    override fun onDestroy() {
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onDestroy()
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
        inflater!!.inflate(R.menu.menu_note, menu)
        menu?.let {
            if (noteFragmentType == NoteFragmentType.VIEW) {
                val itemSave = menu.findItem(R.id.action_save_note)
                val itemEdit = menu.findItem(R.id.action_edit_note)
                itemSave.isVisible = false
                itemEdit.isVisible = true
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
                R.id.action_delete_note -> viewModel.deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onPictureDialogResponse(response: Int) {
        if (response == 1) {
            if ((activity as MainActivity).checkCameraPermissions()) {
                openCamera()
            }
        } else if (response == 2) {
            if ((activity as MainActivity).checkGalleryPermissions()) {
                showGallery()
            }
        }
    }

    fun showGallery() {
        startActivity(GalleryActivity.newIntent(context!!))
    }

    fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { photoIntent ->
            photoIntent.resolveActivity(activity!!.packageManager)?.also {
                val photoFile: File? = try { createImageFile(context!!) } catch(e: IOException) { null }
                photoFile?.also {
                    photoUri = FileProvider.getUriForFile(activity!!, BuildConfig.APPLICATION_ID, it)
                    photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    fun startEditMode() {
        noteFragmentType = NoteFragmentType.EDIT
        noteBinding.noteDateTextView.isEnabled = true
        noteBinding.noteTitleEditText.isEnabled = true
        noteBinding.noteBodyEditText.isEnabled = true
        noteBinding.noteAddImagesButton.visibility = View.VISIBLE
        val itemEdit = menu.findItem(R.id.action_edit_note)
        val itemUpdate = menu.findItem(R.id.action_update_note)
        val itemDelete = menu.findItem(R.id.action_delete_note)
        itemEdit.isVisible = false
        itemUpdate.isVisible = true
        itemDelete.isVisible = true

    }

    fun startViewMode() {
        noteBinding.noteDateTextView.isEnabled = false
        noteBinding.noteTitleEditText.isEnabled = false
        noteBinding.noteBodyEditText.isEnabled = false
        noteBinding.noteAddImagesButton.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            notePhotoAdapter.addItem(photoUri)
            viewModel.notePhotoUris = arrayListOf(photoUri)
            addPhotoToGallery(context!!, photoUri)
        }
    }

}