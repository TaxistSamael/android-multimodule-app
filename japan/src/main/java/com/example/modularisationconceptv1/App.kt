package com.example.modularisationconceptv1

import android.app.Application
import com.example.modularisationconceptv1.deps.featureDepsModule
import com.example.modularisationconceptv1.navigation.navigationModule
import com.example.photo_picker.di.photoPickerModule
import com.example.profile.di.profileModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                profileModule,
                photoPickerModule,
                featureDepsModule,
                navigationModule
            )
        }
    }
}