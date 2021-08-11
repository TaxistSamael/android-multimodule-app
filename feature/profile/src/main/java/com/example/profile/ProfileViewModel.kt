package com.example.profile

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
    private val router: ProfileRouter,
) : ViewModel() {

    private val _liveData = MutableLiveData<UserProfile>()
    val liveData: LiveData<UserProfile> = _liveData

    init {
        observePhotos()
    }

    fun openPhotoPicker() {
        router.goToPhotoPickerFragment(initialUserProfile.id)
    }

    private fun observePhotos() = viewModelScope.launch {
        deps.photoSelections(initialUserProfile.id)
            .scan(initialUserProfile) { userProfile, newPhotoUrl ->
                userProfile.copy(photoUrl = newPhotoUrl)
            }
            .collect {
                _liveData.value = it
            }
    }
}