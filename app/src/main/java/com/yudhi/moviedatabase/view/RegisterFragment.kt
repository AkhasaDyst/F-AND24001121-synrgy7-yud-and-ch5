package com.yudhi.moviedatabase.view

import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.databinding.FragmentRegisterBinding
import com.yudhi.moviedatabase.helper.MyDataStore
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.etUsername.editText
        val passwordEditText = binding.etPassword.editText
        val passwordEditText2 = binding.etPassword2.editText
        val emailEditText = binding.etEmail.editText

        binding.btnRegister.setOnClickListener {
            val enteredUsername = usernameEditText?.text.toString()
            val enteredPassword = passwordEditText?.text.toString()
            val enteredPassword2 = passwordEditText2?.text.toString()
            val enteredEmail = emailEditText?.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                if (enteredPassword == enteredPassword2 && enteredUsername != null && enteredEmail != null) {
                    MyDataStore.saveAccount(requireContext(), enteredUsername, enteredPassword, enteredEmail)
                    view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Toast.makeText(requireContext(), "WRONG PASSWORD", Toast.LENGTH_SHORT).show()
                }
            }
            // Perform some validation here if needed


        }

        binding.tvAkun.setOnClickListener{
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }
}