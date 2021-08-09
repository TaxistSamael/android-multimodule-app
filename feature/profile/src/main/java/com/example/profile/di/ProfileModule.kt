package com.example.profile.di

import com.example.profile.api.ProfileApi
import com.example.profile.model.UserProfile
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.profile.ProfileViewModel

val profileModule = module {
    factory { ProfileApi() }
    viewModel {  (initialProfile: UserProfile) ->
        ProfileViewModel(initialProfile, get())
    }
}