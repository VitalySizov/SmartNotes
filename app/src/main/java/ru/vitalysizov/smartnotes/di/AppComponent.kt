package ru.vitalysizov.smartnotes.di

import dagger.Component
import ru.vitalysizov.smartnotes.presentation.AppActivity
import ru.vitalysizov.smartnotes.presentation.details_note.view.DetailsNoteFragment
import ru.vitalysizov.smartnotes.presentation.edit_note.view.EditNoteFragment
import ru.vitalysizov.smartnotes.presentation.list_notes.view.ListNotesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: AppActivity)

    fun inject(fragment: ListNotesFragment)

    fun inject(fragment: DetailsNoteFragment)

    fun inject(fragment: EditNoteFragment)
}