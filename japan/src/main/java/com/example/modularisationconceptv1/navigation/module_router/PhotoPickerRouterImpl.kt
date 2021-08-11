package com.example.modularisationconceptv1.navigation.module_router

import com.example.modularisationconceptv1.navigation.router.DoterraRouter
import com.example.photo_picker.PhotoPickerRouter

class PhotoPickerRouterImpl(
    private val router: DoterraRouter
): PhotoPickerRouter {

    override fun exit() {
        router.exit()
    }
}