package ru.vitalysizov.smartnotes.data.database

import androidx.room.*
import ru.vitalysizov.smartnotes.domain.models.NoteItem

@Dao
interface NotesDao {

    @Query("SELECT * from NoteItem")
    fun getNotes(): List<NoteItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteItem): Long

    @Query("DELETE from NoteItem where id = :noteId")
    fun deleteItemById(noteId: Int)

    @Update
    fun updateNote(note: NoteItem)

    @Query("SELECT * FROM NoteItem where id =:noteId")
    fun getNoteItemById(noteId: Int): NoteItem

}