package com.example.nikyshop.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nikyshop.R
import com.example.nikyshop.databinding.FragmentSigninBinding
import com.example.nikyshop.model.utils.Methods
import com.example.nikyshop.viewmodel.user.UserViewModel
import com.example.nikyshop.viewmodel.user.UserViewModelProvider

class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding
    lateinit var userViewModel: UserViewModel
    var inputEditTexts: Boolean = false
    private val TAG = "SigninFragment"
    //string error for respone 0 user from database
    val errorNotUser: String =
        "androidx.room.EmptyResultSetException: Query returned empty result set: SELECT * FROM tbl_user WHERE userName == ? and password == ?"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //initialize viewmodel
        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(requireContext())
        ).get(UserViewModel::class.java)

        //initialize binding view and return view root
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //method hidden text password and show
        checkIsShowPasswords()

        //button go to signup fragment
        binding.buttonGoToSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerEntrance, SignupFragment())
                .commit()
        }

        //button signin
        binding.buttonSignInSignIn.setOnClickListener {
            //check inputs not empty
            checkInputEditTexts()
            if (inputEditTexts) {

                //query to database for get user by this user pass
                userViewModel.getUserWithUserPass(
                    binding.editTextUserNameSignIn.text.toString(),
                    binding.editTextPasswordSignIn.text.toString()
                ).observe(requireActivity(), Observer { user ->

                    //go to MainActivity and send user is login
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                    requireActivity().finish()
                })

                //query get errors respone
                userViewModel.getErrors().observe(requireActivity(), Observer {

                    //check error not find user
                    if (it == errorNotUser) {
                        Toast.makeText(
                            requireContext(),
                            "Username or Password is failed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Unknown Error!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

        }

    }

    private fun checkIsShowPasswords() {
        binding.btnShowPasswordSigninActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextPasswordSignIn);
            }
            binding.editTextPasswordSignIn.setSelection(binding.editTextPasswordSignIn.getText().length);
        }
    }

    private fun checkInputEditTexts() {
        if (binding.editTextUserNameSignIn.text.isEmpty()) {
            binding.editTextUserNameSignIn.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextPasswordSignIn.text.isEmpty()) {
            binding.editTextPasswordSignIn.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextPasswordSignIn.text.isNotEmpty() && binding.editTextUserNameSignIn.text.isNotEmpty()) {
            inputEditTexts = true
        }
    }

}