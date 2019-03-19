package lgcode.me.travelnotes.core.di

import lgcode.me.travelnotes.features.main.MainViewModel
import lgcode.me.travelnotes.features.noteslist.usecase.GetNotesListUseCase
import lgcode.me.travelnotes.features.noteslist.NotesListViewModel
import lgcode.me.travelnotes.features.noteslist.usecase.CreateNoteUseCase
import lgcode.me.travelnotes.features.noteslist.usecase.DeleteNoteUseCase
import me.lgcode.balance.core.repository.local.room.TravelNotesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    //Database
    single { TravelNotesDatabase.getInstance(androidApplication()) }

    //UseCases
    single { GetNotesListUseCase(get<TravelNotesDatabase>().entryDao()) }
    single { CreateNoteUseCase(get<TravelNotesDatabase>().entryDao()) }
    single { DeleteNoteUseCase(get<TravelNotesDatabase>().entryDao()) }

    //ViewModels
    viewModel { NotesListViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }

}