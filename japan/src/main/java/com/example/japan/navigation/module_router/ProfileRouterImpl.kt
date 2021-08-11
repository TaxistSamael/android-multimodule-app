package com.example.japan.navigation.module_router

import com.example.japan.navigation.router.DoterraRouter
import com.example.japan.navigation.screen.factory.ScreenFactory
import com.example.profile.ProfileRouter

class ProfileRouterImpl(
    private val router: DoterraRouter,
    private val screenFactory: ScreenFactory,
): ProfileRouter {

    override fun goToPhotoPickerFragment(profileId: String) {
        router.navigateTo(screenFactory.goToPhotoPicker(profileId))
    }
}