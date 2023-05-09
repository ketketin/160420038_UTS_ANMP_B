package com.katheryn.a160420038_uts_anmp_b.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.RequestQueue
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
//import com.katheryn.a160420038_uts_anmp_b.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private var queue: RequestQueue? = null
    private lateinit var viewModel: UserViewModel
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

            it.toolbar.visibility = View.GONE
            it.bottomNav.visibility = View.GONE
            it.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtUsernameLogin.setText("username")
        txtPasswordLogin.setText("password")

        btnLogin.setOnClickListener {
            val username = tilUsernameLogin.editText?.text.toString()
            val password = tilPasswordLogin.editText?.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()){
                if(username == "Katheryn" && password == "kath123"){
                    viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                    val action = LoginFragmentDirections.actionKostList(username)
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }
    }
}