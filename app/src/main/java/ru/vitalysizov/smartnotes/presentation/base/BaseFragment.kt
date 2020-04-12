package ru.vitalysizov.smartnotes.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import dagger.Lazy
import moxy.MvpAppCompatFragment
import ru.vitalysizov.smartnotes.R
import javax.inject.Inject

abstract class BaseFragment<Presenter : BasePresenter<*>> : MvpAppCompatFragment(), IBaseView {

    @Inject
    open lateinit var lazyPresenter: Lazy<Presenter>

    abstract fun performInject()

    abstract val layoutId: Int

    protected lateinit var appActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appActivity = context as BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performInject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar?>(R.id.toolbar)
        toolbar?.setNavigationOnClickListener { appActivity.onBackPressed() }
    }

    override fun back() {
        appActivity.onBackPressed()
    }
}