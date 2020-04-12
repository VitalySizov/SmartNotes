package ru.vitalysizov.smartnotes.presentation.details_note.mvp

import moxy.InjectViewState
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.domain.repository.NotesRepository
import ru.vitalysizov.smartnotes.presentation.base.BasePresenter
import ru.vitalysizov.smartnotes.presentation.details_note.view.DetailsNoteFragmentArgs
import ru.vitalysizov.smartnotes.presentation.details_note.view.IDetailsNoteView
import ru.vitalysizov.smartnotes.utils.ioToUi
import javax.inject.Inject

@InjectViewState
class DetailsNotePresenter @Inject constructor(
    private val listNotesRepository: NotesRepository
) : BasePresenter<IDetailsNoteView>() {

    lateinit var args: DetailsNoteFragmentArgs
    lateinit var noteItem: NoteItem

    override fun attachView(view: IDetailsNoteView?) {
        super.attachView(view)
        loadNoteById()
    }

    private fun loadNoteById() {
        launch {
            listNotesRepository.getNoteById(args.noteId)
                .ioToUi()
                .subscribe(this::handleSuccessLoadNoteById, this::handleError)
        }
    }

    private fun handleSuccessLoadNoteById(item: NoteItem) {
        noteItem = item
        viewState.setTitle(item.title)
        viewState.setText(item.text)
    }

    fun onDeleteNoteClick() {
        viewState.showDeleteDialog(noteItem.title)
    }

    fun deleteNote() {
        launch {
            listNotesRepository.deleteItemById(noteId = noteItem.id)
                .ioToUi()
                .subscribe(this::handleSuccessDeleteNote, this::handleError)
        }
    }

    private fun handleSuccessDeleteNote() {
        viewState.back()
    }
}