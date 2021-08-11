package com.example.japan.navigation.router

import com.example.japan.navigation.router.command.BackToOrReplaceWith
import com.example.japan.navigation.router.command.ReplaceWithBackStack
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
