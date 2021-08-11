package com.doterra.doterramobileshop.navigation.transitions.shared

import androidx.transition.Transition

data class SharedTransitionInfo(
    val sharedElementEnterTransition: Transition,
    val sharedElementReturnTransition: Transition,
    val sharedElements: Map<SharedElementId, SharedElement>
)

data class SharedElementId(
    val name: String
)

data class SharedElement(
    val targetViewId: Int,
    val targetViewType: TargetViewType,
)

enum class TargetViewType {
  From,
  To,
  BothDirections
}