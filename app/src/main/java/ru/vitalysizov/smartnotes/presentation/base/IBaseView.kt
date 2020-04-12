package ru.vitalysizov.smartnotes.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface IBaseView : MvpView  {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun back()
}