package ru.vitalysizov.smartnotes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.vitalysizov.smartnotes.data.database.NotesDao
import ru.vitalysizov.smartnotes.data.database.NotesDatabase
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(): NotesDatabase {
        return Room.databaseBuilder(applicationContext, NotesDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.getNotesDao
    }
}