package com.example.photo_picker.api

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.photo_picker.PhotoPickerFragment
import com.example.photo_picker.model.Photo
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.photo_picker.model.PhotoSelection
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class PhotoPickerApi {

    val photoPickerFlow = MutableStateFlow(PhotoSelection(
        selectionId = "1",
        photo = Photo.presets[0]
    ))

    fun photoPickerFragment(args: PhotoPickerArgs): Fragment =
        PhotoPickerFragment.newInstance(args)

    internal suspend fun postPhotoSelection(photoSelection: PhotoSelection) {
        Log.d("ololo", "PhotoPickerApi. emit")
        photoPickerFlow.emit(photoSelection)
    }

}