package com.example.photo_picker.api

import com.example.photo_picker.model.PhotoSelection
import kotlinx.coroutines.flow.MutableSharedFlow

class PhotoPickerApi {

    val photoPickerFlow = MutableSharedFlow<PhotoSelection>(
        replay = 1,
    )

    internal suspend fun postPhotoSelection(photoSelection: PhotoSelection) {
        photoPickerFlow.emit(photoSelection)
    }
}