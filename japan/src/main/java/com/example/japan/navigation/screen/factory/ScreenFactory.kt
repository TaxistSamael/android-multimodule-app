package com.example.japan.navigation.screen.factory

import com.example.japan.navigation.screen.SupportFragmentScreen
import com.example.photo_picker.PhotoPickerFragment
import com.example.photo_picker.model.PhotoPickerArgs
import com.example.profile.ProfileFragment
import com.example.profile.model.UserProfile

interface ScreenFactory {
    fun goToProfile(userProfile: UserProfile = UserProfile.predefined1): SupportFragmentScreen
    fun goToPhotoPicker(selectionId: String): SupportFragmentScreen
}

class DefaultScreenFactory: ScreenFactory {
    override fun goToProfile(userProfile: UserProfile) = SupportFragmentScreen {
        ProfileFragment.newInstance(userProfile)
    }

    override fun goToPhotoPicker(selectionId: String) = SupportFragmentScreen {
        PhotoPickerFragment.newInstance(PhotoPickerArgs(selectionId))
    }

}