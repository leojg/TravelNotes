package lgcode.me.travelnotes.features.noteslist

import androidx.lifecycle.MutableLiveData
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import lgcode.me.travelnotes.features.noteslist.usecase.GetNotesListUseCase
import me.lgcode.balance.core.repository.Result

class NotesListViewModel(val getNotesListUseCase: GetNotesListUseCase): BaseViewModel() {

    val notesListLiveData = MutableLiveData<List<Note>>()

    fun fetchNotes() = getNotesListUseCase {
        when(it) {
            is Result.Success -> notesListLiveData.value = it.value
            else -> handleError(it as Result.Failure)
        }
    }

}