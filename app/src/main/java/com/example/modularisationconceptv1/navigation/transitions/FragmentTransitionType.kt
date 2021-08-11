package com.example.modularisationconceptv1.navigation.transitions

import com.example.modularisationconceptv1.R

enum class FragmentTransitionType(val enter: Int, val exit: Int, val popEnter: Int, val popExit: Int) {
  None(R.anim.nothing, R.anim.nothing, R.anim.nothing, R.anim.nothing),
  Fade(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out),
  Slide(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out),
  SlideBack(R.anim.slide_right_in, R.anim.slide_right_out, R.anim.slide_left_in, R.anim.slide_left_out),
  CoverDown(R.anim.slide_down_in, R.anim.nothing, R.anim.nothing, R.anim.slide_up_out),
}

fun FragmentTransitionType.toFragmentAnimatorHolder(): FragmentAnimatorHolder {
  return FragmentAnimatorHolder(
      enter = enter,
      exit = exit,
      popEnter = popEnter,
      popExit = popExit,
  )
}
