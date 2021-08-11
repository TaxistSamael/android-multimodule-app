package com.example.photo_picker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photo_picker.api.PhotoPickerApi
import com.example.photo_picker.model.Photo
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.photo_picker.model.PhotoSelection
import kotlinx.coroutines.launch

internal class PhotoPickerViewModel(
    private val photoPickerArgs: PhotoPickerArgs,
    private val api: PhotoPickerApi,
    private val router: PhotoPickerRouter,
): ViewModel() {

    fun onPhotoSelected(photo: Photo) = viewModelScope.launch {
        api.postPhotoSelection(
            PhotoSelection(
                selectionId = photoPickerArgs.selectionId,
                photo = photo
            )
        )
        router.exit()
    }
}