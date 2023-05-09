package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.view.adapter.KostListAdapter
import com.katheryn.a160420038_uts_anmp_b.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private  lateinit var viewModel: BookmarkViewModel
    private val kostListAdapter = KostListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Global.fragment = "FavoriteFragment"

        viewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.rvKostFavorite)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = kostListAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutFavorite)
        refreshLayout.setOnRefreshListener {
            refreshLayout.visibility = View.GONE
            txtError.visibility = View.GONE
            progressBarKostFavorit.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFavorite.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.bookmarLD.observe(viewLifecycleOwner, Observer {
            kostListAdapter.updateKostList(it)
        })

        viewModel.bookmarkLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                txtError.visibility = View.VISIBLE
            } else{
                txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                rvKostFavorite.visibility = View.GONE
                progressBarKostFavorit.visibility = View.VISIBLE
            } else{
                rvKostFavorite.visibility = View.VISIBLE
                progressBarKostFavorit.visibility = View.GONE
            }
        })
    }
}