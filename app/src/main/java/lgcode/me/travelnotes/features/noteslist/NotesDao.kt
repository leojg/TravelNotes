package lgcode.me.travelnotes.features.noteslist

import androidx.room.*
import lgcode.me.travelnotes.core.domain.NotesEntity
import me.lgcode.balance.core.repository.local.BaseDao

@Dao
interface NotesDao: BaseDao<NotesEntity?> {

    @Query("SELECT * FROM notes LIMIT 1")
    override fun get(): NotesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(input: NotesEntity?): Long?

    @Delete
    override fun remove(input: NotesEntity?): Int

    @Query("SELECT * FROM notes")
    override fun getAll(): List<NotesEntity?>

    @Update
    override fun update(input: NotesEntity?): Int


}