package lgcode.me.travelnotes.core.usecase

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.mapper.NotesMapper
import lgcode.me.travelnotes.features.noteslist.NotesDao
import lgcode.me.travelnotes.core.repository.Result

class GetNotesListUseCase(val notesDao: NotesDao): UseCase<List<Note>, Void>() {
    override suspend fun run(params: Void?): Result<List<Note>> {
        val result = notesDao.getAll()

        //TODO: Should check if list items are null?
        return when(result.size) {
            0 -> Result.Success(emptyList())
            else -> { Result.Success(result.map { NotesMapper.mapFrom(it!!)}) }
        }

    }
}