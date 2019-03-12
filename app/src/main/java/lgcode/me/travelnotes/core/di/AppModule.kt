package lgcode.me.travelnotes.core.di

import lgcode.me.travelnotes.features.noteslist.NotesListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    //viewModels
    viewModel { NotesListViewModel() }

}