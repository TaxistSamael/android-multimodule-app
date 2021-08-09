package com.example.profile.api

import androidx.fragment.app.Fragment
import com.example.profile.model.UserProfile
import com.example.profile.ProfileFragment

class ProfileApi {
    fun profileFragment(userProfile: UserProfile): Fragment =
        ProfileFragment.newInstance(userProfile)
}