package ru.vitalysizov.smartnotes.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.vitalysizov.smartnotes.data.database.NotesDao
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.domain.repository.NotesRepository
import javax.inject.Inject

class NotesDBRepository @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {

    override fun getNotes(): Single<List<NoteItem>> {
        return Single.fromCallable { notesDao.getNotes() }
    }

    override fun insertNote(note: NoteItem): Single<Long> {
        return Single.fromCallable { notesDao.insertNote(note) }
    }

    override fun deleteItemById(noteId: Int): Completable {
        return Completable.fromCallable { notesDao.deleteItemById(noteId) }
    }

    override fun updateNote(note: NoteItem): Completable {
        return Completable.fromCallable { notesDao.updateNote(note) }
    }

    override fun getNoteById(noteId: Int): Single<NoteItem> {
        return Single.fromCallable { notesDao.getNoteItemById(noteId) }
    }
}