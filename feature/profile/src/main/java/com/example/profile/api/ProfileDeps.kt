package com.example.profile.api

import kotlinx.coroutines.flow.Flow

interface ProfileDeps {
    fun photoSelections(profileId: String): Flow<String>
}