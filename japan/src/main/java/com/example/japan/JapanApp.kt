package com.example.japan

import android.app.Application
import com.multimodule.monolith.deps.featureDepsModule
import com.example.japan.navigation.navigationModule
import com.example.photo_picker.di.photoPickerModule
import com.example.profile.di.profileModule
import org.koin.core.context.startKoin

class JapanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val koinApplication = startKoin {
            modules(
                profileModule,
                photoPickerModule,
                featureDepsModule,
                navigationModule
            )
        }


    }
}