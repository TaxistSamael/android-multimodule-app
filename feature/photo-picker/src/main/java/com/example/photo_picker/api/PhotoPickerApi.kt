package com.example.photo_picker.api

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.photo_picker.PhotoPickerFragment
import com.example.photo_picker.model.Photo
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.photo_picker.model.PhotoSelection
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class PhotoPickerApi {

    val photoPickerFlow = MutableSharedFlow<PhotoSelection>(
        replay = 100,
        extraBufferCapacity = 100,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun photoPickerFragment(args: PhotoPickerArgs): Fragment =
        PhotoPickerFragment.newInstance(args)

    internal suspend fun postPhotoSelection(photoSelection: PhotoSelection) {
        Log.d("ololo", "PhotoPickerApi. emit")
        photoPickerFlow.tryEmit(photoSelection)
    }

}