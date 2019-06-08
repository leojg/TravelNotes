package lgcode.me.travelnotes.features.noteslist

import androidx.lifecycle.MutableLiveData
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import lgcode.me.travelnotes.core.usecase.GetNotesListUseCase
import lgcode.me.travelnotes.core.repository.Result
import lgcode.me.travelnotes.core.usecase.DeleteNoteUseCase

class NotesListViewModel(val getNotesListUseCase: GetNotesListUseCase,
                         val deleteNoteUseCase: DeleteNoteUseCase): BaseViewModel() {

    val notesListLiveData = MutableLiveData<List<Note>>()
    val deleteStatusLiveData = MutableLiveData<Boolean>()

    fun fetchNotes() = getNotesListUseCase {
        when(it) {
            is Result.Success -> notesListLiveData.value = it.value
            else -> handleError(it as Result.Failure)
        }
    }

    fun deleteNote(note: Note) = deleteNoteUseCase(note) {
        when(it) {
            is Result.Success -> deleteStatusLiveData.value = true
            else -> handleError(it as Result.Failure)
        }
    }

}