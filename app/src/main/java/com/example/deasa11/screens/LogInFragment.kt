package com.example.deasa11.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.deasa11.R
import com.example.deasa11.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        binding.btnLogIn.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                binding.edEmail.text.toString(),
                binding.edPassword.text.toString()
            ).addOnCompleteListener { Task ->
                if (Task.isSuccessful) {
                    findNavController().navigate(R.id.action_logInFragment_to_startFragment)
                }
            }
        }

        binding.tvRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registrationFragment)
        }
    }

}