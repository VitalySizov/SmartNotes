package ru.vitalysizov.smartnotes.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.vitalysizov.smartnotes.domain.models.NoteItem

interface NotesRepository {

    fun getNotes(): Single<List<NoteItem>>

    fun insertNote(note: NoteItem): Single<Long>

    fun updateNote(note: NoteItem): Completable

    fun deleteItemById(noteId: Int): Completable

    fun getNoteById(noteId: Int): Single<NoteItem>

}