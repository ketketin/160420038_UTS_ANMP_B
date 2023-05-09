package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kost_photos.*

class KostPhotosFragment : Fragment() {
    private lateinit var photosViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var kostID = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            kostID = KostPhotosFragmentArgs.fromBundle(requireArguments()).id
        }

        photosViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        photosViewModel.fetch(kostID)
        observeViewModel()
    }

    fun observeViewModel(){
        photosViewModel.kostLD.observe(viewLifecycleOwner){
            photoUrl.loadImage(it.photoUrl, progressBarPhotoURL)
            photo1.loadImage(it.photo1, progressBarPhoto1)
            photo2.loadImage(it.photo2, progressBarPhotos2)
            photo3.loadImage(it.photo3, progressBarPhotos3)
        }
    }

}