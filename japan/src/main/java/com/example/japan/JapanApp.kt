package com.example.japan

import android.app.Application
import com.multimodule.monolith.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JapanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JapanApp)
            modules(appComponent)
        }
    }
}