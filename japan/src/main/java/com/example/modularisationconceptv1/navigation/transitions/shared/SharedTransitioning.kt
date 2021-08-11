package com.doterra.doterramobileshop.navigation.transitions.shared

interface SharedTransitioning {
  /**
   * Get specific shared transition to run for the fragments that are
   * entering and exiting in this transaction.
   */
  fun getSharedTransitionInfo(): SharedTransitionInfo
}