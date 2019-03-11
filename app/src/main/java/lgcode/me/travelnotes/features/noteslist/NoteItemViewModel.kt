package lgcode.me.travelnotes.features.noteslist

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NoteItemViewModel(val note: Note): BaseViewModel() {
    val summary = note.text.substring(50).plus("...")
    val date = DateFormat.getDateInstance().format(note.date)
}