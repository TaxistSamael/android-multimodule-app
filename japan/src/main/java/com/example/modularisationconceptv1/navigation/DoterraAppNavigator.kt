package com.example.modularisationconceptv1.navigation

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.doterra.doterramobileshop.navigation.transitions.Transitioning
import com.doterra.doterramobileshop.navigation.transitions.shared.SharedTransitioning
import com.doterra.doterramobileshop.navigation.transitions.shared.TargetViewType
import com.example.modularisationconceptv1.navigation.router.command.BackToOrReplaceWith
import com.example.modularisationconceptv1.navigation.router.command.ReplaceWithBackStack
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.*

class DoterraAppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int,
) : AppNavigator(activity, containerId, fragmentManager) {
  constructor(activity: AppCompatActivity, containerId: Int) :
      this(activity, activity.supportFragmentManager, containerId)

  override fun applyCommands(commands: Array<out Command>) {
    hideKeyboard(activity)
    applyCommandsSync(commands)
  }

  fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    hideKeyboardIfNoViewsHaveFocus(activity, inputMethodManager)
  }

  private fun hideKeyboardIfNoViewsHaveFocus(
      activity: Activity,
      inputMethodManager: InputMethodManager,
  ) {
    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let {
      inputMethodManager.hideSoftInputFromWindow(
          currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
  }

  override fun applyCommand(command: Command) {
    when (command) {
      is BackToOrReplaceWith -> backToOrReplaceWith(command)
      is ReplaceWithBackStack -> replaceWithBackStack(command)
      else -> super.applyCommand(command)
    }
  }


  private fun replaceWithBackStack(command: ReplaceWithBackStack) {
    when (val screen = command.screen) {
      is FragmentScreen -> {
        if (localStackCopy.isNotEmpty()) {
          commitNewFragmentScreen(screen, addToBackStack = true)
        } else {
          commitNewFragmentScreen(screen, addToBackStack = false)
        }
      }
    }
  }

  private fun backToOrReplaceWith(command: BackToOrReplaceWith) {
    val key: String = command.screen.screenKey
    val index = localStackCopy.indexOfFirst { it == key }

    if (index != -1) {
      backTo(BackTo(command.screen))
    } else {
      replace(Replace(command.screen))
    }
  }

  override fun setupFragmentTransaction(
      screen: FragmentScreen,
      fragmentTransaction: FragmentTransaction,
      currentFragment: Fragment?,
      nextFragment: Fragment,
  ) {
    setupTransition(nextFragment, fragmentTransaction)
    setupSharedTransition(currentFragment, nextFragment, fragmentTransaction)
  }

  private fun setupTransition(
      nextFragment: Fragment?,
      fragmentTransaction: FragmentTransaction,
  ) {
    if (nextFragment is Transitioning) {
      val animatorHolder = nextFragment.getAnimatorHolder()
      fragmentTransaction.setCustomAnimations(
          animatorHolder.enter,
          animatorHolder.exit,
          animatorHolder.popEnter,
          animatorHolder.popExit,
      )
    }
  }

  private fun setupSharedTransition(
      currentFragment: Fragment?,
      nextFragment: Fragment?,
      fragmentTransaction: FragmentTransaction,
  ) {
    if (currentFragment is SharedTransitioning && nextFragment is SharedTransitioning) {
      val fromInfo = currentFragment.getSharedTransitionInfo()
      val toInfo = nextFragment.getSharedTransitionInfo()

      val fromSharedElements = fromInfo.sharedElements.filter { it.value.targetViewType != TargetViewType.To }
      val toSharedElements = toInfo.sharedElements.filter { it.value.targetViewType != TargetViewType.From }

      fromSharedElements.entries
          .filter { entry -> toSharedElements.containsKey(entry.key) }
          .mapNotNull { currentFragment.view?.findViewById(it.value.targetViewId) }
          .forEach { view -> fragmentTransaction.addSharedElement(view, view.transitionName) }

      currentFragment.sharedElementEnterTransition = fromInfo.sharedElementEnterTransition
      currentFragment.sharedElementReturnTransition = fromInfo.sharedElementReturnTransition
      nextFragment.sharedElementEnterTransition = toInfo.sharedElementEnterTransition
      nextFragment.sharedElementReturnTransition = toInfo.sharedElementReturnTransition
    }
  }
}
