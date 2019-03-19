package lgcode.me.travelnotes.features.noteslist

import androidx.room.*
import lgcode.me.travelnotes.core.domain.NoteEntity
import me.lgcode.balance.core.repository.local.BaseDao

@Dao
interface NotesDao: BaseDao<NoteEntity?> {

    @Query("SELECT * FROM notes LIMIT 1")
    override fun get(): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(input: NoteEntity?): Long?

    @Delete
    override fun remove(input: NoteEntity?): Int

    @Query("SELECT * FROM notes")
    override fun getAll(): List<NoteEntity?>

    @Update
    override fun update(input: NoteEntity?): Int


}