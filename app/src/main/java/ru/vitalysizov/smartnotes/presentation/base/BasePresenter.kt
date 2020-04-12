package ru.vitalysizov.smartnotes.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.vitalysizov.smartnotes.BuildConfig

abstract class BasePresenter<View : IBaseView> : MvpPresenter<View>() {

    private val compositeDisposable = CompositeDisposable()

    fun launch(disposable: () -> Disposable) {
        compositeDisposable.add(disposable.invoke())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    protected fun handleError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        }
    }
}