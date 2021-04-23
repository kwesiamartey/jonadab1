package com.silasandcoltd.iamp2021


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.silasandcoltd.iamp2021.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    lateinit var _bind:FragmentSignInBinding
    val bind get() = _bind

    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _bind = FragmentSignInBinding.inflate(layoutInflater)

        mAuth = FirebaseAuth.getInstance();

        val edtEmail = bind.edtEmail
        val edtPassword = bind.edtPassword
        val login = bind.login
        val progress_b = bind.progressB
        val txt_signip = bind.txtSignupPage

        login.setOnClickListener {
            progress_b.visibility = ViewAnimator.VISIBLE

            if(edtEmail.text.toString().isEmpty() || edtPassword.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Email or password must not be empty", Toast.LENGTH_LONG).show()
                edtEmail.requestFocus()
            }else {

                mAuth.signInWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            findNavController().navigate(R.id.recyclerViewFragment)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        txt_signip.setOnClickListener {
           findNavController().navigate(R.id.signUpFragment)
        }


        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "Sign In"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser:FirebaseUser?){

    }
}