package com.multimodule.monolith.navigation.screen

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import java.io.Serializable

// We require these wrappers to pass them through Bundle

fun interface SupportCreator<A, R> : Serializable {
  fun create(argument: A): R
}

interface SerializableAppScreen : Serializable

class SupportFragmentScreen(
    private val key: String? = null,
    private val fragmentCreator: SupportCreator<FragmentFactory, Fragment>
) : FragmentScreen, SerializableAppScreen, Serializable {
  override val screenKey: String
    get() = key ?: super.screenKey

  override fun createFragment(factory: FragmentFactory): Fragment {
    return fragmentCreator.create(factory)
  }
}

class SupportActivityScreen(
    private val key: String? = null,
    private val intentCreator: SupportCreator<Context, Intent>
) : ActivityScreen, SerializableAppScreen, Serializable {
  override val screenKey: String
    get() = key ?: super.screenKey

  override fun createIntent(context: Context): Intent {
    return intentCreator.create(context)
  }
}
