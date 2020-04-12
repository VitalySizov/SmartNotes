package ru.vitalysizov.smartnotes

import android.app.Application
import ru.vitalysizov.smartnotes.di.AppComponent
import ru.vitalysizov.smartnotes.di.AppModule
import ru.vitalysizov.smartnotes.di.DaggerAppComponent
import ru.vitalysizov.smartnotes.di.RepositoryModule

class AppApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule())
            .build()
    }
}