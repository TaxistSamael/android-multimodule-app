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
    private val photoPickerApi: PhotoPickerApi,
): ViewModel() {

    fun onPhotoSelected(photo: Photo) = viewModelScope.launch {
        photoPickerApi.postPhotoSelection(
            PhotoSelection(
                selectionId = photoPickerArgs.selectionId,
                photo = photo
            )
        )
    }
}