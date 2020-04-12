package ru.vitalysizov.smartnotes.di

import dagger.Module
import dagger.Provides
import ru.vitalysizov.smartnotes.data.database.NotesDao
import ru.vitalysizov.smartnotes.data.repository.NotesDBRepository
import ru.vitalysizov.smartnotes.domain.repository.NotesRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNotesDBRepository(notesDao: NotesDao): NotesRepository {
        return NotesDBRepository(notesDao)
    }
}