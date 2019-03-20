package lgcode.me.travelnotes.features.noteslist

import androidx.room.*
import lgcode.me.travelnotes.core.domain.NoteEntity
import lgcode.me.travelnotes.core.repository.local.BaseDao

@Dao
interface NotesDao: BaseDao<NoteEntity?> {

    @Query("SELECT * FROM notes")
    override fun getAll(): List<NoteEntity?>

    @Query("SELECT * FROM notes WHERE uid = :id LIMIT 1")
    override fun getById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(input: NoteEntity?): Long?

    @Update
    override fun update(input: NoteEntity?): Int

    @Delete
    override fun remove(input: NoteEntity?): Int


}