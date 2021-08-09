package com.example.modularisationconceptv1.deps

import androidx.fragment.app.Fragment
import com.example.photo_picker.api.PhotoPickerApi
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.profile.api.ProfileDeps
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class ProfileDepsImpl(
    private val photoPickerApi: PhotoPickerApi
): ProfileDeps {

    override fun photoPickerFragment(profileId: String): Fragment {
        return photoPickerApi.photoPickerFragment(PhotoPickerArgs((profileId)))
    }

    override fun photoSelections(profileId: String): Flow<String> {
        return photoPickerApi.photoPickerFlow
            .filter { it.selectionId == profileId }
            .map { it.photo.url }
    }

}