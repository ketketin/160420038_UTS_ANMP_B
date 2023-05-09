package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostListAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.KostListViewModel
import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kost_list.*


class KostListFragment : Fragment() {
    private lateinit var kostListViewModel: KostListViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.moveTaskToBack(true)
                }
            })
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Global.fragment = "KostListFragment"
        kostListViewModel = ViewModelProvider(this).get(KostListViewModel::class.java)

        kostListViewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.rvListKost)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = kostListAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutKostList)
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            kostListViewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        kostListViewModel.kostLD.observe(viewLifecycleOwner, Observer {
          kostListAdapter.updateKostList(it)
        })

        kostListViewModel.kostLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        kostListViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                rvListKost.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                rvListKost.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
    }


}