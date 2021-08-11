package com.example.photo_picker.api

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.photo_picker.PhotoPickerFragment
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.photo_picker.model.PhotoSelection
import kotlinx.coroutines.flow.MutableSharedFlow

class PhotoPickerApi {

    val photoPickerFlow = MutableSharedFlow<PhotoSelection>(
        replay = 1,
    )

    fun photoPickerFragment(args: PhotoPickerArgs): Fragment =
        PhotoPickerFragment.newInstance(args)

    internal suspend fun postPhotoSelection(photoSelection: PhotoSelection) {
        photoPickerFlow.emit(photoSelection)
    }
}