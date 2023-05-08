package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
//import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    companion object{
        var username = ""
    }
//    private lateinit var viewModel: UserViewModel

    fun observeViewModel(){
//        viewModel.userLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            imgProfileSettings.loadImage(it.photoUrl, progressBarSettings)
//            txtName.setText(it.username)
//            txtID.setText(it.id)
//            txtPhone.setText(it.phone)
//        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.toolbar.visibility = View.VISIBLE
            it.bottomNav.visibility = View.VISIBLE

            it.toolbar.title = "Settings"
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = SettingsFragmentArgs.fromBundle(requireArguments()).id
//        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        viewModel.fetch()

        observeViewModel()
    }
}