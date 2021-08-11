package com.multimodule.mexico

import android.app.Application
import com.multimodule.monolith.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MexicoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MexicoApp)
            modules(appComponent)
        }
    }
}