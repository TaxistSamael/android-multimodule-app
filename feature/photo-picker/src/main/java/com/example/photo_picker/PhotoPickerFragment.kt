package com.example.photo_picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_utils.args
import com.example.android_utils.withArgs
import com.example.photo_picker.model.Photo
import com.example.photo_picker.model.PhotoPickerArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf

class PhotoPickerFragment: BottomSheetDialogFragment(), KoinComponent {

    private val photoPickerArgs by args<PhotoPickerArgs>()
    private val viewModel: PhotoPickerViewModel by viewModel {
        parametersOf(photoPickerArgs)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.photo_recycler_view).apply {
            val photoAdapter = PhotoAdapter { photo ->
                viewModel.onPhotoSelected(photo)
            }
            adapter = photoAdapter
            layoutManager = GridLayoutManager(context, 3)
            photoAdapter.submitList(Photo.presets)
        }
    }

    companion object {
        fun newInstance(photoPickerArgs: PhotoPickerArgs) = PhotoPickerFragment().withArgs(photoPickerArgs)
    }
}