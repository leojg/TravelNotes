package me.lgcode.balance.core.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lgcode.me.travelnotes.core.domain.NoteEntity
import lgcode.me.travelnotes.features.noteslist.NotesDao

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TravelNotesDatabase: RoomDatabase() {

    abstract fun entryDao(): NotesDao

    companion object {

        private var INSTANCE: TravelNotesDatabase? = null

        fun getInstance(context: Context): TravelNotesDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, TravelNotesDatabase::class.java, "travelnotes.db").build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}