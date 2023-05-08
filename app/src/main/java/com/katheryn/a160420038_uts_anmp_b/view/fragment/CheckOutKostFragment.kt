package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.katheryn.a160420038_uts_anmp_b.R
import kotlinx.android.synthetic.main.fragment_check_out_kost.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class CheckOutKostFragment : Fragment() {
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

        btnCheckOut.setOnClickListener {
            val action = CheckOutKostFragmentDirections.actionMyKostFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}