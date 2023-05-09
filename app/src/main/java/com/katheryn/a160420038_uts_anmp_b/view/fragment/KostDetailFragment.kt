package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
//import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostDetailAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_kost_photos.*

class KostDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    fun observeViewModel(){
        viewModel.kostLD.observe(viewLifecycleOwner, Observer {
            imageDetailKost.loadImage(it.photoUrl, progressBarKostDetail)
            txtNameKostDetail.setText(it.name)
            txtAddressKostDetail.setText(it.address)
            txtDescriptionKostDetail.setText(it.description)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Global.fragment = "KostDetailFragment"
        var id = ""
        arguments?.let {
            id = KostDetailFragmentArgs.fromBundle(requireArguments()).id
        }

        btnPhotos.setOnClickListener {
            val actionPhotos = KostDetailFragmentDirections.actionPhotosFragment()
            Navigation.findNavController(view).navigate(actionPhotos)
        }

        btnFasilitas.setOnClickListener {
            val actionFasilitas = KostDetailFragmentDirections.actionFacilitiesFragment(id)
            Navigation.findNavController(view).navigate(actionFasilitas)
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)
        observeViewModel()
    }

}