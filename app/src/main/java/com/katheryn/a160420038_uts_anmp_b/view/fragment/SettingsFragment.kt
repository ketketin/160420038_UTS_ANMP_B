package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
//import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner){
            it?.let {
                txtUsernameSettings.text = it.username
                txtUserIDSettings.text = it.id
                txtPhoneSettings.text = it.phone
                imgProfileSettings.loadImage(it.photoUrl, progressBarProfile)
            }
        }
    }
}