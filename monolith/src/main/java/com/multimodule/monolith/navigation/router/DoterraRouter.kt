package com.multimodule.monolith.navigation.router

import com.multimodule.monolith.navigation.router.command.BackToOrReplaceWith
import com.multimodule.monolith.navigation.router.command.ReplaceWithBackStack
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
