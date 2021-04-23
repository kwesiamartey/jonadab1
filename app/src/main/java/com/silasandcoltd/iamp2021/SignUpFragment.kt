package com.silasandcoltd.iamp2021

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.silasandcoltd.iamp2021.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    lateinit var _bind:FragmentSignUpBinding
    val bind get() = _bind

    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _bind = FragmentSignUpBinding.inflate(layoutInflater)

        mAuth = FirebaseAuth.getInstance();

        val edtEmail = bind.edtEmail
        val edtPassword = bind.edtPassword
        val progress_b = bind.progressB

        _bind.signup.setOnClickListener {
            progress_b.visibility = ViewAnimator.VISIBLE
            if(edtEmail.text.toString().isEmpty() || edtPassword.text.toString().isEmpty()){
                   Toast.makeText(requireContext(), "Email or password must not be empty", Toast.LENGTH_LONG).show()
                   edtEmail.requestFocus()
                progress_b.visibility = ViewAnimator.GONE
            }else{
                mAuth.createUserWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            progress_b.visibility = ViewAnimator.GONE
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(requireContext(), "Authentication Successful.",
                                Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "createUserWithEmail:success")
                            findNavController().navigate(R.id.signInFragment)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        _bind.txtSignupPage.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "Sign Up"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?){

    }
}