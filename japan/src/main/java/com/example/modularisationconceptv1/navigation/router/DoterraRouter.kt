package com.example.modularisationconceptv1.navigation.router

import com.example.modularisationconceptv1.navigation.router.command.BackToOrReplaceWith
import com.example.modularisationconceptv1.navigation.router.command.ReplaceWithBackStack
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

class DoterraRouter : Router() {
  fun backToOrReplaceWith(screen: Screen) {
    executeCommands(BackToOrReplaceWith(screen))
  }

  fun replaceAndAddToBackStack(screen: Screen) {
    executeCommands(ReplaceWithBackStack(screen))
  }
}
