package com.multimodule.monolith.navigation.router.command

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

class ReplaceWithBackStack(
    val screen: Screen
) : Command
