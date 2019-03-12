package lgcode.me.travelnotes.features.noteslist

import androidx.lifecycle.MutableLiveData
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import java.util.*

class NotesListViewModel: BaseViewModel() {

    val notesListLiveData = MutableLiveData<List<Note>>()

    fun fetchNotes() {
        val notesList = ArrayList<Note>()

        val note1 = Note("summary", Date(), null)
        val note2 = Note("summary2", Date(), null)
        val note3 = Note("summary long long ago, but not as much as I would love", Date(), null)
        val note4 = Note("summar4", Date(), null)

        notesList.add(note1)
        notesList.add(note2)
        notesList.add(note3)
        notesList.add(note4)

        notesListLiveData.value = notesList

    }

}