package lgcode.me.travelnotes.features.noteslist

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import lgcode.me.travelnotes.core.domain.NoteEntity
import lgcode.me.travelnotes.core.repository.local.BaseDao

@Dao
interface NotesDao: BaseDao<NoteEntity?> {

    @Query("SELECT * FROM notes")
    override fun getAll(): List<NoteEntity?>

    @Query("SELECT * FROM notes WHERE uid = :id LIMIT 1")
    override fun getById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insert(input: NoteEntity?): Long?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun update(input: NoteEntity?): Int

    @Delete
    override fun remove(input: NoteEntity?): Int


}