package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.viewmodel.CheckoutViewModel
import kotlinx.android.synthetic.main.fragment_check_out_kost.*
import kotlinx.android.synthetic.main.fragment_detail_checkout.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class DetailCheckoutFragment : Fragment() {
    private lateinit var checkoutDetailViewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var kostID = ""
        arguments?.let {
            kostID = DetailCheckoutFragmentArgs.fromBundle(requireArguments()).id
        }

        btnPesan.setOnClickListener {
            val action = DetailCheckoutFragmentDirections.actionDetailCheckouttoItemHome()
            Navigation.findNavController(view).navigate(action)
        }

        checkoutDetailViewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        checkoutDetailViewModel.fetch(kostID)
        observeViewModel()
    }

    fun observeViewModel(){
        checkoutDetailViewModel.checkoutLD.observe(viewLifecycleOwner, Observer {
            imgDetailCheckout.loadImage(it.photoUrl, progressBarDetailCheckout)
            txtNamaDetailCheckout.setText(it.name)
            txtAddressDetailCheckout.setText(it.address)
            txtTotalPesananDetailCheckout.setText(it.price)
            txtPriceDetailCheckOut.setText(it.price)
        })
    }

}