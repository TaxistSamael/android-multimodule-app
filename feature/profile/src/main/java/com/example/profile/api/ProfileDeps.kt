package com.example.profile.api

import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow

interface ProfileDeps {
    fun photoPickerFragment(profileId: String): Fragment
    fun photoSelections(profileId: String): Flow<String>
}