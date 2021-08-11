package com.example.photo_picker.di

import com.example.photo_picker.PhotoPickerViewModel
import com.example.photo_picker.api.PhotoPickerApi
import com.example.photo_picker.model.PhotoPickerArgs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoPickerModule = module {
    viewModel { (args: PhotoPickerArgs) -> PhotoPickerViewModel(args, get()) }
    single { PhotoPickerApi() }
}