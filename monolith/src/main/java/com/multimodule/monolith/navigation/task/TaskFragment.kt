package com.multimodule.monolith.navigation.task

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.transition.AutoTransition
import com.multimodule.monolith.navigation.router.DoterraRouter
import com.doterra.doterramobileshop.navigation.transitions.shared.SharedTransitionInfo
import com.doterra.doterramobileshop.navigation.transitions.shared.SharedTransitioning
import com.example.android_utils.argumentDelegate
import com.multimodule.monolith.navigation.DoterraAppNavigator
import com.multimodule.monolith.navigation.transitions.OnBackPressedListener
import com.multimodule.monolith.navigation.router.type.RouterType
import com.multimodule.monolith.navigation.screen.SerializableAppScreen
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.multimodule.monolith.R
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class TaskFragment : Fragment(), OnBackPressedListener, SharedTransitioning {
  private val ciceroneHolder: TaskCiceroneHolder by inject()
  private val rootRouter: DoterraRouter by inject(named(RouterType.Root))

  private val taskName: String by argumentDelegate()
  private val initialChain: Array<SerializableAppScreen> by argumentDelegate()

  private lateinit var navigator: DoterraAppNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ciceroneHolder.setCurrentTask(taskName)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View? {
    return inflater.inflate(R.layout.activity_fragment_container, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navigator = DoterraAppNavigator(requireActivity(), childFragmentManager, R.id.containerFragment)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    lifecycleScope.launchWhenResumed {
      if (childFragmentManager.findFragmentById(R.id.containerFragment) == null) {
        getTaskRouter().newRootChain(*initialChain.filterIsInstance<Screen>().toTypedArray())
      }
    }
  }

  fun getCurrentFragment(): Fragment? {
    return try {
      childFragmentManager.fragments.firstOrNull { it.isVisible }
    } catch (ex: IllegalStateException) {
      null
    }
  }

  override fun onResume() {
    super.onResume()
    ciceroneHolder.setCurrentTask(taskName)
    ciceroneHolder.getCurrentTaskCicerone().getNavigatorHolder().setNavigator(navigator)
  }

  override fun onPause() {
    super.onPause()
    ciceroneHolder.getCicerone(taskName).getNavigatorHolder().removeNavigator()
  }

  override fun onDestroy() {
    super.onDestroy()
    ciceroneHolder.finishTask(taskName)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    childFragmentManager.findFragmentById(R.id.containerFragment)
        ?.onActivityResult(requestCode, resultCode, data)
  }

  override fun onBackPressed(): Boolean {
    val currentFragment = childFragmentManager.findFragmentById(R.id.containerFragment) as? OnBackPressedListener

    if (currentFragment?.onBackPressed() != true) {
      rootRouter.exit()
    }
    return true
  }

  private fun getTaskRouter(): Router {
    return ciceroneHolder.getCicerone(taskName).router
  }

  override fun getSharedTransitionInfo(): SharedTransitionInfo {
    val childFragment = getCurrentFragment()
    return if (childFragment is SharedTransitioning) {
      childFragment.getSharedTransitionInfo()
    } else {
      val defaultTransition = AutoTransition()
      SharedTransitionInfo(defaultTransition, defaultTransition, emptyMap())
    }
  }

  companion object {
    fun newInstance(taskName: String, vararg initialChain: SerializableAppScreen): TaskFragment {
      if (initialChain.isEmpty()) {
        throw IllegalArgumentException("`initialChain` should not be empty.")
      }
      return TaskFragment().apply {
        arguments = bundleOf(
            this::taskName.name to taskName,
            this::initialChain.name to initialChain
        )
      }
    }
  }
}
