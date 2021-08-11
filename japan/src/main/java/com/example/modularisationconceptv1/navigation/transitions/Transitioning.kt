package com.doterra.doterramobileshop.navigation.transitions

import com.example.modularisationconceptv1.navigation.transitions.DefaultFragmentAnimatorHolder
import com.example.modularisationconceptv1.navigation.transitions.FragmentAnimatorHolder

interface Transitioning {
  /**
   * Get specific animation resources to run for the fragments that are
   * entering and exiting in this transaction.
   */
  fun getAnimatorHolder(): FragmentAnimatorHolder {
    return DefaultFragmentAnimatorHolder
  }
}