package com.masliaiev.preparingforthereview

import android.app.Application
import com.masliaiev.preparingforthereview.di.koinModule
import com.masliaiev.preparingforthereview.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PreparingApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PreparingApp)
            modules(listOf(koinModule, viewModelModule))
        }
    }
}