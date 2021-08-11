package com.example.modularisationconceptv1.gluing_modules

import com.example.photo_picker.api.PhotoPickerApi
import com.example.profile.api.ProfileDeps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class ProfileDepsImpl(
    private val photoPickerApi: PhotoPickerApi,
): ProfileDeps {

    override fun photoSelections(profileId: String): Flow<String> {
        return photoPickerApi.photoPickerFlow
            .filter { it.selectionId == profileId }
            .map { it.photo.url }
    }

}