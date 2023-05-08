package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostListAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_kost_facilities.*

class KostFacilitiesFragment : BottomSheetDialogFragment() {
    private lateinit var fasilitasViewModel : KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    var kostID = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            kostID = KostFacilitiesFragmentArgs.fromBundle(requireArguments()).id
        }

        fasilitasViewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
        fasilitasViewModel.fasilitas()

        observeViewModel()
    }

    private fun observeViewModel(){
        fasilitasViewModel.fasilitasLD.observe(viewLifecycleOwner){
            for((index, item) in it.withIndex()){
                if(item.KostID == Global.kostID){
                    txtKamarFasilitas.text = item.tipe_kamar
                    txtKasurFasilitas.text = item.tempat_tidur
                    txtPendinginFasilitas.text = item.pendingin
                    txtFurnitureFasilitas.text = item.furniture
                }
            }
        }
    }

}