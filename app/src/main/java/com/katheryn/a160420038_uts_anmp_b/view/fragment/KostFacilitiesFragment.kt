package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_facilities.*

class KostFacilitiesFragment : BottomSheetDialogFragment() {
    private lateinit var fasilitasViewModel : DetailViewModel

    var kostID = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let{
            kostID = KostFacilitiesFragmentArgs.fromBundle(requireArguments()).id
        }

        fasilitasViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        fasilitasViewModel.fetch(kostID)

        observeViewModel()
    }

    private fun observeViewModel(){
        fasilitasViewModel.kostLD.observe(viewLifecycleOwner){
            txtKamarFasilitas.setText(it.tipe_kamar)
            txtKasurFasilitas.setText(it.tempat_tidur)
            txtPendinginFasilitas.setText(it.pendingin)
            txtFurnitureFasilitas.setText(it.furniture)
        }
    }
}