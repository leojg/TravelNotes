package lgcode.me.travelnotes.core.mapper

import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.domain.NoteEntity

object NotesMapper: BaseMapper<Note, NoteEntity> {
    override fun mapTo(input: Note) = NoteEntity(text = input.text, date = input.date, image = input.image)
    override fun mapFrom(input: NoteEntity) = Note(text = input.text, date = input.date, image = input.image)
}