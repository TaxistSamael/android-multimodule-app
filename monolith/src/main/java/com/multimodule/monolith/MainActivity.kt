package com.multimodule.monolith

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimodule.monolith.navigation.DoterraAppNavigator
import com.multimodule.monolith.navigation.router.DoterraRouter
import com.multimodule.monolith.navigation.router.type.RouterType
import com.multimodule.monolith.navigation.screen.factory.ScreenFactory
import com.multimodule.monolith.navigation.transitions.OnBackPressedListener
import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity(R.layout.activity_fragment_container) {

    private val navigatorHolder: NavigatorHolder by inject()
    private val screensFactory: ScreenFactory by inject()
    private val rootRouter: DoterraRouter by inject(named(RouterType.Root))
    private val navigator = DoterraAppNavigator(this, R.id.containerFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.DoterraTheme)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            rootRouter.newRootScreen(screensFactory.goToProfile())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.containerFragment)
        if ((currentFragment as? OnBackPressedListener)?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }
}