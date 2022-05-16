package com.example.deasa11.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.deasa11.R
import com.example.deasa11.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    private lateinit var mAuth: FirebaseAuth
    lateinit var dt: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        val firebaseDatabase = FirebaseFirestore.getInstance()
//        if (mAuth.currentUser != null) {
//            firebaseDatabase.collection("users").get().addOnSuccessListener { querySnapshot ->
//                querySnapshot.forEach { document ->
//                    dt = document.data.get("name").toString()
//                }
//                Toast.makeText(context, "Welcom $dt", Toast.LENGTH_SHORT).show()
//            }
//
//        }

        binding.btnRegistration.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                binding.edEmail.text.toString(),
                binding.edPassword.text.toString()
            )
                .addOnCompleteListener { Task ->
                    if (Task.isSuccessful) {
                        Toast.makeText(context, "Registration", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_startFragment)
                        val firbaseUser = FirebaseAuth.getInstance().currentUser
                        val hashMap = hashMapOf<String, Any>(
                            "firstName" to binding.edFirstName.text.toString(),
                            "lastName" to binding.edLastName.text.toString(),
                            "email" to binding.edEmail.text.toString()
                        )


                        firebaseDatabase.collection("users").document(firbaseUser!!.uid)
                            .set(hashMap)
                            .addOnSuccessListener {
                                Toast.makeText(context, "add data base", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    } else {
                        Toast.makeText(context, "error Registration", Toast.LENGTH_SHORT).show()
                    }
                }
        }

//
//        mAuth.createUserWithEmailAndPassword(
//            binding.edEmail.text.toString(),
//            binding.edPassword.text.toString()
//        ).addOnCompleteListener { Task ->
//            if (Task.isSuccessful) {
//                Toast.makeText(context, "Registration", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "error Registration", Toast.LENGTH_SHORT).show()
//            }
//        }
        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }
    }
}
