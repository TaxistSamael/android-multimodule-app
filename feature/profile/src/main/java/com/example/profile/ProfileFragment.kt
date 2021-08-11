package com.example.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_utils.args
import com.example.android_utils.withArgs
import com.example.profile.model.UserProfile
import org.koin.androidx.viewmodel.ext.android.viewModel
import coil.load
import com.example.profile.api.ProfileDeps
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val userProfile by args<UserProfile>()
    private val viewModel: ProfileViewModel by viewModel {
        parametersOf(userProfile)
    }
    private val deps: ProfileDeps by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoView = view.findViewById<ImageView>(R.id.profile_photo)
        val nameView = view.findViewById<TextView>(R.id.profile_name)
        val changePhotoButton = view.findViewById<Button>(R.id.change_profile_photo)

        viewModel.liveData.observe(viewLifecycleOwner) { userProfile ->
            if (userProfile == null) return@observe
            photoView.load(userProfile.photoUrl)
            nameView.text = userProfile.name
        }

        changePhotoButton.setOnClickListener { openFragment() }
    }

    private fun openFragment() {
        val fragment = deps.photoPickerFragment(userProfile.id)
        parentFragmentManager
            .beginTransaction()
            .add(fragment, "PhotoPicker")
            .commit()
    }

    companion object {
        fun newInstance(userProfile: UserProfile) = ProfileFragment().withArgs(userProfile)
    }
}
