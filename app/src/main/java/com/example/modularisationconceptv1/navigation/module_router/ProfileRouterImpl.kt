package com.example.modularisationconceptv1.navigation.module_router

import com.example.modularisationconceptv1.navigation.router.DoterraRouter
import com.example.modularisationconceptv1.navigation.screen.factory.ScreenFactory
import com.example.profile.ProfileRouter

class ProfileRouterImpl(
    private val router: DoterraRouter,
    private val screenFactory: ScreenFactory,
): ProfileRouter {

    override fun goToPhotoPickerFragment(profileId: String) {
        router.navigateTo(screenFactory.goToPhotoPicker(profileId))
    }
}