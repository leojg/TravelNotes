package lgcode.me.travelnotes.features.main

import kotlinx.coroutines.GlobalScope
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import lgcode.me.travelnotes.features.noteslist.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.features.noteslist.usecase.DeleteNoteUseCase
import java.util.*

class MainViewModel(
    val createNoteUseCase: CreateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
): BaseViewModel() {

}