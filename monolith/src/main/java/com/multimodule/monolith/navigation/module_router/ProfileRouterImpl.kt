package com.multimodule.monolith.navigation.module_router

import com.multimodule.monolith.navigation.router.DoterraRouter
import com.multimodule.monolith.navigation.screen.factory.ScreenFactory
import com.example.profile.ProfileRouter

class ProfileRouterImpl(
    private val router: DoterraRouter,
    private val screenFactory: ScreenFactory,
): ProfileRouter {

    override fun goToPhotoPickerFragment(profileId: String) {
        router.navigateTo(screenFactory.goToPhotoPicker(profileId))
    }
}