package com.multimodule.monolith.deps

import com.example.photo_picker.api.PhotoPickerDeps
import com.example.profile.api.ProfileDeps
import org.koin.dsl.module

val featureDepsModule = module {
    single<ProfileDeps> { ProfileDepsImpl(get()) }
    single<PhotoPickerDeps> { PhotoPickerDepsImpl() }
}