package ru.vitalysizov.smartnotes.presentation.list_notes.view

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.presentation.base.IBaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface IListNotesView : IBaseView {

    fun setItems(items: List<NoteItem>)

    fun addNoteItem(item: NoteItem)

    fun deleteItem(item: NoteItem, position: Int)

    fun showEmptyList(show: Boolean)

    fun clearItems()
}