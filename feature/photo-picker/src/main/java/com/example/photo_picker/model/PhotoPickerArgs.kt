package com.example.photo_picker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoPickerArgs(
    val selectionId: String
) : Parcelable