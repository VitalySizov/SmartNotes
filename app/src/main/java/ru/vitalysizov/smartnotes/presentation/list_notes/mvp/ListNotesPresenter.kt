package ru.vitalysizov.smartnotes.presentation.list_notes.mvp

import moxy.InjectViewState
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.domain.repository.NotesRepository
import ru.vitalysizov.smartnotes.presentation.base.BasePresenter
import ru.vitalysizov.smartnotes.presentation.list_notes.view.IListNotesView
import ru.vitalysizov.smartnotes.utils.ioToUi
import javax.inject.Inject

@InjectViewState
class ListNotesPresenter @Inject constructor(
    private val listNotesRepository: NotesRepository
) : BasePresenter<IListNotesView>() {

    private var listNotes = arrayListOf<NoteItem>()

    override fun attachView(view: IListNotesView?) {
        super.attachView(view)
        loadNotes()
    }

    fun addNoteItem() {
        val noteItem = NoteItem(title = "Новая заметка", text = "")
        launch {
            listNotesRepository.insertNote(noteItem)
                .ioToUi()
                .subscribe({
                    noteItem.id = it.toInt()
                    handleSuccessAddNoteItem(noteItem)
                }, this::handleError)
        }
    }

    private fun handleSuccessAddNoteItem(item: NoteItem) {
        listNotes.add(item)
        viewState.showEmptyList(false)
        viewState.addNoteItem(item)
    }

    private fun loadNotes() {
        launch {
            listNotesRepository.getNotes()
                .ioToUi()
                .subscribe(this::handleSuccessLoadNotes, this::handleError)
        }
    }

    private fun handleSuccessLoadNotes(items: List<NoteItem>) {
        if (items.isEmpty()) {
            viewState.showEmptyList(true)
            return
        }

        listNotes.clear()
        listNotes.addAll(items)
        viewState.clearItems()
        viewState.showEmptyList(false)
        viewState.setItems(items)
    }

    fun deleteNote(item: NoteItem, position: Int) {
        launch {
            listNotesRepository.deleteItemById(item.id)
                .ioToUi()
                .subscribe({ handleSuccessDeleteNote(item, position) }, this::handleError)
        }
    }

    private fun handleSuccessDeleteNote(item: NoteItem, position: Int) {
        listNotes.remove(item)
        viewState.deleteItem(item, position)
        if (listNotes.isEmpty()) {
            viewState.showEmptyList(true)
        }
    }

}