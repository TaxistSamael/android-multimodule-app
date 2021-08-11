package com.multimodule.monolith

import com.example.photo_picker.di.photoPickerModule
import com.example.profile.di.profileModule
import com.multimodule.monolith.deps.featureDepsModule

val appComponent = listOf(
    profileModule,
    photoPickerModule,
    featureDepsModule,
    navigationModule
)