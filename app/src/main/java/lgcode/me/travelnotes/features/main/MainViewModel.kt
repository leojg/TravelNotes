package lgcode.me.travelnotes.features.main

import lgcode.me.travelnotes.core.viewmodel.BaseViewModel
import lgcode.me.travelnotes.core.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.core.usecase.DeleteNoteUseCase

class MainViewModel(
    val createNoteUseCase: CreateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
): BaseViewModel() {

}