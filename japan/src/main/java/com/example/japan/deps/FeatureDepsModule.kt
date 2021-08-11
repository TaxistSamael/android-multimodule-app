package com.example.japan.deps

import com.example.photo_picker.api.PhotoPickerDeps
import com.example.profile.api.ProfileDeps
import com.multimodule.monolith.deps.ProfileDepsImpl
import org.koin.dsl.module

val featureDepsModule = module {
    single<ProfileDeps> { ProfileDepsImpl(get()) }
    single<PhotoPickerDeps> { com.multimodule.monolith.deps.PhotoPickerDepsImpl() }
}