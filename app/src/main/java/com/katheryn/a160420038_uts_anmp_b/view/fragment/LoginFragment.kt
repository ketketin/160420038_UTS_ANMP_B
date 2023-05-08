package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.katheryn.a160420038_uts_anmp_b.R
//import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private var queue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        txtUsernameLogin.setText("username")
        txtPasswordLogin.setText("password")

        btnLogin.setOnClickListener {
            val username = tilUsernameLogin.editText?.text.toString()
            val password = tilPasswordLogin.editText?.text.toString()
        }
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

            it.toolbar.visibility = View.GONE
            it.bottomNav.visibility = View.GONE
            it.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        txtRegister.setOnClickListener {
//            val action = LoginFragmentDirections.actionRegister()
//            Navigation.findNavController(it).navigate(action)
//        }

//        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//        btnLogin.setOnClickListener { btnView ->
//            val username = txtUsernameLogin.text.toString()
//            val password = txtPasswordLogin.text.toString()
//
//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                viewModel.login(username, password, btnView)
//            } else {
//                Toast.makeText(requireContext(), "Username or password cannot be empty", Toast.LENGTH_SHORT).show()
//            }
//        }

        btnLogin.setOnClickListener {
            val username = txtUsernameLogin.text.toString()
            val password = txtPasswordLogin.text.toString()
            val action = LoginFragmentDirections.actionKostList(username)
            Navigation.findNavController(it).navigate(action)
        }
    }
}