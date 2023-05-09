package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.view.adapter.CheckoutAdapter
import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostListAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.CheckoutViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_my_kost.*

class MyKostFragment : Fragment() {
    private lateinit var viewModel: CheckoutViewModel
    private val checkoutAdapter = CheckoutAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_kost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Global.fragment = "CheckoutFragment"
        viewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.rvMyKost)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = checkoutAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutMyKost)
        refreshLayoutMyKost.setOnRefreshListener {
            refreshLayout.visibility = View.GONE
            txtErrorMyKost.visibility = View.GONE
            progressBarMyKost.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutMyKost.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.checkoutLD.observe(viewLifecycleOwner, Observer {
            checkoutAdapter.updateCheckoutList(it)
        })

        viewModel.checkoutLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                txtErrorMyKost.visibility = View.VISIBLE
            } else{
                txtErrorMyKost.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                rvMyKost.visibility = View.GONE
                progressBarMyKost.visibility = View.VISIBLE
            } else{
                rvMyKost.visibility = View.VISIBLE
                progressBarMyKost.visibility = View.GONE
            }
        })
    }

}