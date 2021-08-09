package com.example.modularisationconceptv1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.profile.api.ProfileApi
import com.example.profile.model.UserProfile
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class RootFragment: Fragment(R.layout.fragment_root) {

    private val profileApi: ProfileApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            attachFragment("profile1") {
                profileApi.profileFragment(UserProfile.predefined1)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.profile1 -> attachFragment("profile1") {
                    profileApi.profileFragment(UserProfile.predefined1)
                }
                R.id.profile2 -> attachFragment("profile2") {
                    profileApi.profileFragment(UserProfile.predefined2)
                }
            }
            true
        }
    }

    private fun attachFragment(tag: String, createFragment: () -> Fragment) {
        childFragmentManager.apply {
            val currentFragment = findFragmentById(R.id.fragment_container)
            val fragmentToAttach = findFragmentByTag(tag)

            beginTransaction()
                .apply {
                    if (currentFragment != null) {
                        detach(currentFragment)
                    }
                    if (fragmentToAttach == null) {
                        add(R.id.fragment_container, createFragment(), tag)
                    }
                    else {
                        attach(fragmentToAttach)
                    }
                }
                .commit()
        }
    }
}