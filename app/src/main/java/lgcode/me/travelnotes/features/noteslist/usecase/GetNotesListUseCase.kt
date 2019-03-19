package lgcode.me.travelnotes.features.noteslist.usecase

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.mapper.NotesMapper
import lgcode.me.travelnotes.features.noteslist.NotesDao
import me.lgcode.balance.core.repository.Result
import me.lgcode.balance.core.usecase.UseCase

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