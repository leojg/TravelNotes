package lgcode.me.travelnotes.core.mapper

import android.net.Uri
import lgcode.me.travelnotes.core.domain.Note
import lgcode.me.travelnotes.core.domain.NoteEntity

object NotesMapper: BaseMapper<Note, NoteEntity> {
    override fun mapTo(input: Note): NoteEntity {

        val uriStrings: ArrayList<String>? = if (input.images.isNullOrEmpty()) {
            null
        } else {
            val imagesList = ArrayList<String>(input.images!!.size)
            input.images?.forEach {
                imagesList.add(it.toString())
            }
            imagesList
        }

        return if (input.id != null) {
            NoteEntity(uid = input.id, title = input.title, body = input.body, date = input.date, images = uriStrings)
        } else {
            NoteEntity(title = input.title, body = input.body, date = input.date, images = uriStrings)
        }
    }
    override fun mapFrom(input: NoteEntity): Note {
        val uriList: ArrayList<Uri>? = if (input.images.isNullOrEmpty()) {
            null
        } else {
            val imagesList = ArrayList<Uri>(input.images!!.size)
            input.images?.forEach {
                imagesList.add(Uri.parse(it))
            }
            imagesList
        }
        return Note(id = input.uid, title = input.title, body = input.body, date = input.date, images = uriList)
    }
}