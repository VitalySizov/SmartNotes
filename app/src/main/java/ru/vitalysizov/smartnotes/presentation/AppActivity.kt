package ru.vitalysizov.smartnotes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.vitalysizov.smartnotes.AppApplication
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.presentation.base.BaseActivity

class AppActivity : BaseActivity() {

    companion object {
        fun startWithClearStack(context: Context) {
            val intent = Intent(context, AppActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun performInject() {
        AppApplication.appComponent.inject(this)
    }
}