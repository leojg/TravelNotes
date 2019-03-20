package lgcode.me.travelnotes.core.usecase

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.mapper.NotesMapper
import lgcode.me.travelnotes.features.noteslist.NotesDao
import lgcode.me.travelnotes.core.repository.Result

class CreateNoteUseCase(val notesDao: NotesDao): UseCase<Void, Note>() {
    override suspend fun run(params: Note?): Result<Void> {
        return if (params != null) {
            when(notesDao.insert(NotesMapper.mapTo(params))) {
                0L, null -> Result.Failure(Throwable())
                else -> Result.Success()
            }
        } else {
            Result.Failure(Throwable())
        }
    }
}