package com.example.japan.navigation.module_router

import com.example.japan.navigation.router.DoterraRouter
import com.example.photo_picker.PhotoPickerRouter

class PhotoPickerRouterImpl(
    private val router: DoterraRouter
): PhotoPickerRouter {

    override fun exit() {
        router.exit()
    }
}