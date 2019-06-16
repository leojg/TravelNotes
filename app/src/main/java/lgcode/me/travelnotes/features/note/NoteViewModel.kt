package lgcode.me.travelnotes.features.note

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.repository.Result
import lgcode.me.travelnotes.core.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.core.usecase.DeleteNoteUseCase
import lgcode.me.travelnotes.core.usecase.UpdateNoteUseCase
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import java.util.*
import kotlin.collections.ArrayList

class NoteViewModel(
    val createNoteUseCase: CreateNoteUseCase,
    val updateNoteUserCase: UpdateNoteUseCase,
    val deleteNoteUserCase: DeleteNoteUseCase): BaseViewModel() {

    val operationStatus = MutableLiveData<Boolean>()

    val noteTitle = ObservableField<String>()
    val noteBody = ObservableField<String>()
    val noteCalendar = MutableLiveData<Calendar>()

    var notePhotoUris: MutableList<Uri>? = null

    private lateinit var note: Note

    init {
        noteCalendar.value = Calendar.getInstance()
    }

    fun saveNote() {
        buildNote()?.let { note ->
            createNoteUseCase(note) {
                when(it) {
                    is Result.Success -> operationStatus.value = true
                    is Result.Failure -> {
                        operationStatus.value = false
                        handleError(it)
                    }
                }
            }
        }
    }

    fun updateNote() {
        buildNote()?.let { note ->
            updateNoteUserCase(note) {
                when(it) {
                    is Result.Success -> operationStatus.value = true
                    is Result.Failure -> {
                        operationStatus.value = false
                        handleError(it)
                    }
                }
            }
        }
    }

    fun deleteNote() {
        if (::note.isInitialized) {
            deleteNoteUserCase(note) {
                when(it) {
                    is Result.Success -> operationStatus.value = true
                    is Result.Failure -> {
                        operationStatus.value = false
                        handleError(it)
                    }
                }
            }
        }
    }

    fun updateNotePhotos(notePhotoUris: List<Uri>) {
        this.notePhotoUris = notePhotoUris as ArrayList<Uri>
    }

    private fun buildNote(): Note? {
        if (noteBody.get() == null) {
            error.value = "The note is empty."
            return null
        }

        if (noteCalendar.value == null) {
            error.value = "The date is invalid"
            return null
        }
        val noteDate = noteCalendar.value!!.time

        val note = if (::note.isInitialized) {
            Note(note.id, noteTitle.get(), noteBody.get()!!, noteDate, notePhotoUris)
        } else {
            Note(noteTitle.get(), noteBody.get()!!, noteDate, notePhotoUris)
        }

        return note
    }

    fun setNote(note: Note?) {
        if (note != null) {
            this.note = note
            noteTitle.set(note.title)
            noteBody.set(note.body)
            noteCalendar.value!!.time = note.date
            notePhotoUris = note.images
        } else {
            error.value = "Error showing note"
        }
    }

}