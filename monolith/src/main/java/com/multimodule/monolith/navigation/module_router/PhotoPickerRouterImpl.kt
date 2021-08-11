package com.multimodule.monolith.navigation.module_router

import com.multimodule.monolith.navigation.router.DoterraRouter
import com.example.photo_picker.PhotoPickerRouter

class PhotoPickerRouterImpl(
    private val router: DoterraRouter
): PhotoPickerRouter {

    override fun exit() {
        router.exit()
    }
}