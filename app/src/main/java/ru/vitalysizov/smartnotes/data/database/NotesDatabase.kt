package ru.vitalysizov.smartnotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vitalysizov.smartnotes.domain.models.NoteItem

@Database(entities = [NoteItem::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract val getNotesDao: NotesDao
}