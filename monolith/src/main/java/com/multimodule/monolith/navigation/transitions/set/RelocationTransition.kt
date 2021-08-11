package com.multimodule.monolith.navigation.transitions.set

import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet

class RelocationTransition : TransitionSet() {
  init {
    ordering = ORDERING_TOGETHER
    interpolator = FastOutSlowInInterpolator()
    addTransition(ChangeBounds())
    addTransition(ChangeTransform())
  }
}