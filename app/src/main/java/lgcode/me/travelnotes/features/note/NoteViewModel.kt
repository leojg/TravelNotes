package lgcode.me.travelnotes.features.note

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.repository.Result
import lgcode.me.travelnotes.core.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.core.usecase.DeleteNoteUseCase
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import java.util.*

class NoteViewModel(
    val createNoteUseCase: CreateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase): BaseViewModel() {

    val operationStatus = MutableLiveData<Boolean>()

    val noteTitle = ObservableField<String>()
    val noteBody = ObservableField<String>()
    val noteCalendar = MutableLiveData<Calendar>()

    init {
        noteCalendar.value = Calendar.getInstance()
    }

    fun saveNote() {

        if (noteBody.get() == null) {
            error.value = "The note is empty."
            return
        }

        if (noteCalendar.value == null) {
            error.value = "The date is invalid"
            return
        }
        val noteDate = noteCalendar.value!!.time
        val note = Note(noteTitle.get(), noteBody.get()!!, noteDate)

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