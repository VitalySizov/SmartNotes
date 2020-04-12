package ru.vitalysizov.smartnotes.presentation.edit_note.view

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.vitalysizov.smartnotes.presentation.base.IBaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface IEditNoteView : IBaseView {

    fun setTitle(title: String)

    fun setText(text: String)

    fun showDeleteDialog(title: String)

    fun showCancelDialog()

    fun showSaveDialog()

    fun restartApp()

    fun setCurrentImportanceColor(color: Int)

}