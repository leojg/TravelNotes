package lgcode.me.travelnotes.core.mapper

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.domain.NoteEntity

object NotesMapper: BaseMapper<Note, NoteEntity> {
    override fun mapTo(input: Note): NoteEntity {
        return if (input.id != null) {
            NoteEntity(uid = input.id, title = input.title, body = input.body, date = input.date, image = input.image)
        } else {
            NoteEntity(title = input.title, body = input.body, date = input.date, image = input.image)
        }
    }
    override fun mapFrom(input: NoteEntity) = Note(id = input.uid, title = input.title, body = input.body, date = input.date, image = input.image)
}