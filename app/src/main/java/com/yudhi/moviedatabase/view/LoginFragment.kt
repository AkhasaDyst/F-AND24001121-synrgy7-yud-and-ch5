package com.yudhi.moviedatabase.view

import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.databinding.FragmentLoginBinding
import com.yudhi.moviedatabase.helper.MyDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameEditText = binding.etUsername.editText
        val passwordEditText = binding.etPassword.editText

        binding.btnLoginMovie.setOnClickListener {
            val enteredUsername = usernameEditText?.text.toString()
            val enteredPassword = passwordEditText?.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                MyDataStore.getSavedAccount(requireContext()).collect { (username, password) ->
                    if (username == enteredUsername && password == enteredPassword) {
                        view.findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                        Toast.makeText(requireContext(), "succses", Toast.LENGTH_SHORT).show()
                        Log.d("d", "Navigation to Movie Fragment")
                    } else {
                        Toast.makeText(requireContext(), "INVALID LOGIN", Toast.LENGTH_SHORT).show()
                        Log.d("d", "cantt")
                    }
                }
            }



        }

        binding.tvAkun.setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


}


