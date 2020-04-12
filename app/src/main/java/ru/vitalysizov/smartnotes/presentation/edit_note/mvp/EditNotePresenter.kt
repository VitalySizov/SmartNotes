package ru.vitalysizov.smartnotes.presentation.edit_note.mvp

import moxy.InjectViewState
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.domain.repository.NotesRepository
import ru.vitalysizov.smartnotes.presentation.base.BasePresenter
import ru.vitalysizov.smartnotes.presentation.edit_note.view.EditNoteFragmentArgs
import ru.vitalysizov.smartnotes.presentation.edit_note.view.IEditNoteView
import ru.vitalysizov.smartnotes.utils.ioToUi
import javax.inject.Inject

@InjectViewState
class EditNotePresenter @Inject constructor(
    private val listNotesRepository: NotesRepository
) : BasePresenter<IEditNoteView>() {

    lateinit var args: EditNoteFragmentArgs
    lateinit var noteItem: NoteItem

    private var tempImportanceColor: Int? = null

    override fun attachView(view: IEditNoteView?) {
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
        tempImportanceColor = item.importanceColor
        setUiItems()
    }

    fun deleteNote() {
        launch {
            listNotesRepository.deleteItemById(noteId = noteItem.id)
                .ioToUi()
                .subscribe(this::handleSuccessDeleteNote, this::handleError)
        }
    }

    private fun handleSuccessDeleteNote() {
        viewState.restartApp()
    }

    fun onDeleteNoteClick() {
        viewState.showDeleteDialog(noteItem.title)
    }

    fun onCancelEditClick() {
        viewState.showCancelDialog()
    }

    fun onSaveClick() {
        viewState.showSaveDialog()
    }

    fun setUiItems() {
        viewState.setTitle(noteItem.title)
        viewState.setText(noteItem.text)
        viewState.setCurrentImportanceColor(noteItem.importanceColor)
    }

    fun saveNote(title: String, text: String) {
        noteItem.importanceColor = tempImportanceColor ?: R.color.white

        val updateItem = NoteItem(
            id = noteItem.id,
            title = title,
            text = text,
            importanceColor = noteItem.importanceColor
        )
        noteItem = updateItem
        launch {
            listNotesRepository.updateNote(updateItem)
                .ioToUi()
                .subscribe({ handleSuccessUpdateNote() }, this::handleError)
        }
    }

    private fun handleSuccessUpdateNote() {
        //nothing
    }

    fun choiceColorClick(color: Int) {
        tempImportanceColor = color
        viewState.setCurrentImportanceColor(color)
    }
}