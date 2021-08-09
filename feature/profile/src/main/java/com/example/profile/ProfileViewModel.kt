package com.example.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.api.ProfileDeps
import com.example.profile.model.UserProfile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

internal class ProfileViewModel(
    private val initialUserProfile: UserProfile,
    private val deps: ProfileDeps,
) : ViewModel() {

    private val _liveData = MutableLiveData<UserProfile>()
    val liveData: LiveData<UserProfile> = _liveData

    init {
        observePhotos()
    }

    private fun observePhotos() = viewModelScope.launch {
        deps.photoSelections(initialUserProfile.id)
            .scan(initialUserProfile) { userProfile, newPhotoUrl ->
                Log.d("ololo", "profileViewModel. scan")
                userProfile.copy(photoUrl = newPhotoUrl)
            }
            .collect {
                Log.d("ololo", "profileViewModel. collect")
                _liveData.value = it
            }
    }
}