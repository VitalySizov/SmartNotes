package ru.vitalysizov.smartnotes.presentation.details_note.view

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.vitalysizov.smartnotes.presentation.base.IBaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface IDetailsNoteView : IBaseView {

    fun setTitle(title: String)

    fun setText(text: String)

    fun showDeleteDialog(title: String)

}