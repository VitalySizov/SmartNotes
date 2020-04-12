package ru.vitalysizov.smartnotes.presentation.base

import android.os.Bundle
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {

    abstract fun performInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performInject()
    }
}