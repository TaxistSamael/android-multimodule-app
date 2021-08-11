package com.example.modularisationconceptv1.navigation.screen.factory

import com.github.terrakok.cicerone.Screen

interface ScreenFactory {
    fun goToProfile(): Screen
    fun goToPhotoPicker(): Screen
}

class DefaultScreenFactory: ScreenFactory {
    override fun goToProfile(): Screen {
        TODO("Not yet implemented")
    }

    override fun goToPhotoPicker(): Screen {
        TODO("Not yet implemented")
    }

}