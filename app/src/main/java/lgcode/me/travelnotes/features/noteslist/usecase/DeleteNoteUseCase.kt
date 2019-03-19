package lgcode.me.travelnotes.features.noteslist.usecase

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.mapper.NotesMapper
import lgcode.me.travelnotes.features.noteslist.NotesDao
import me.lgcode.balance.core.repository.Result
import me.lgcode.balance.core.usecase.UseCase

class DeleteNoteUseCase(val notesDao: NotesDao): UseCase<Void, Note>() {
    override suspend fun run(params: Note?): Result<Void> {
        return if (params != null) {
            when(notesDao.remove(NotesMapper.mapTo(params))) {
                0 -> Result.Failure(Throwable())
                else -> Result.Success()
            }
        } else {
            Result.Failure(Throwable())
        }

    }
}