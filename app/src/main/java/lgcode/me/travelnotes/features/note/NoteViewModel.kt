package lgcode.me.travelnotes.features.note

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.core.usecase.DeleteNoteUseCase
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel

class NoteViewModel(
    val createNoteUseCase: CreateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase): BaseViewModel() {

    lateinit var note: Note



}