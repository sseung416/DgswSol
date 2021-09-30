package com.example.a2ndproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.AddAccount1FragmentBinding

class AddAccountFragment : Fragment() {

    private val navController: NavController by lazy {
        findNavController()
    }

    private lateinit var binding: AddAccount1FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_account_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterAddAccount1.setOnClickListener {
            navigateToNewThisAccount()
        }

        binding.btnConnectAddAccount1.setOnClickListener {
            navigateToConnectThisAccount()
        }
    }

    private fun navigateToConnectThisAccount() {
        navController.navigate(R.id.action_addAccount1Fragment_to_connectThisAccountFragment)
    }

    private fun navigateToNewThisAccount() {
        navController.navigate(R.id.action_addAccount1Fragment_to_newThisAccountFragment)
    }
}