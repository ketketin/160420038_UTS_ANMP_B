package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostListAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.CheckoutViewModel
import com.katheryn.a160420038_uts_anmp_b.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_check_out_kost.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class CheckOutKostFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
//    private lateinit var checkviewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_out_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Global.fragment = "CheckoutFragment"
        var kostID=""
        arguments?.let {
            kostID = CheckOutKostFragmentArgs.fromBundle(requireArguments()).id
        }

        btnCheckOut.setOnClickListener {
            val action = CheckOutKostFragmentDirections.actionMyKostFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(kostID)
        observerViewModel()

    }

    fun observerViewModel(){
        viewModel.kostLD.observe(viewLifecycleOwner) {
            imgChekoutKost.loadImage(it.photoUrl, progressBarCheckout)
            txtPriceCheckOut.setText(it.price)
            txtNameCheckputKost.setText(it.name)
            txtTotalCheckOut.setText(it.price)
            txtPriceKostCheckout.setText(it.price)
        }
    }
}