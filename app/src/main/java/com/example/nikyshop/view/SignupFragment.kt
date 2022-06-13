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
import com.example.nikyshop.databinding.FragmentSignupBinding
import com.example.nikyshop.model.entity.User
import com.example.nikyshop.model.utils.Methods
import com.example.nikyshop.viewmodel.user.UserViewModel
import com.example.nikyshop.viewmodel.user.UserViewModelProvider

class SignupFragment : Fragment() {

    lateinit var userViewModel: UserViewModel
    lateinit var binding: FragmentSignupBinding
    var inputEditTexts: Boolean = false



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

        //initialize and return binding view
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //method hidden text password and show
        checkIsShowPasswords()

        //button go to signin page
        binding.btnIHaveAccountSignIn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerEntrance, SigninFragment())
                .commit()
        }

        //button signup
        binding.buttonSignUpSignUp.setOnClickListener {

            //check inputs not empty
            checkInputEditTexts()
            if (inputEditTexts) {

                //query to database for get all user
                userViewModel.getAllUsers().observe(requireActivity(), Observer { list ->

                    var user: User? = null

                    //check count users at database: if users=0 -> create admin account /////// if users>0 -> create user account
                    if (list.isEmpty()) {
                        user = User(
                            0,
                            binding.editTextUserNameSignIn.text.toString(),
                            binding.editTextPasswordSignIn.text.toString(),
                            true,
                        )
                        //add to database
                        userViewModel.addUser(user)

                        Toast.makeText(
                            requireContext(),
                            "Your Admin!",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        user = User(
                            0,
                            binding.editTextUserNameSignIn.text.toString(),
                            binding.editTextPasswordSignIn.text.toString(),
                            false,
                        )

                        //add to database
                        userViewModel.addUser(user)
                    }

                    //go to MainActivity
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                    requireActivity().finish()
                })
            }
        }
    }

    fun checkInputEditTexts() {
        if (binding.editTextUserNameSignIn.text.isEmpty()) {
            binding.editTextUserNameSignIn.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextPasswordSignIn.text.isEmpty()) {
            binding.editTextPasswordSignIn.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextConfirmPasswordSignIn.text.isEmpty()) {
            binding.editTextConfirmPasswordSignIn.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextConfirmPasswordSignIn.text.toString() != binding.editTextPasswordSignIn.text.toString()) {
            Toast.makeText(
                requireContext(),
                "The passwords are not the same!",
                Toast.LENGTH_SHORT
            ).show()
            inputEditTexts = false
        }
        if (binding.editTextPasswordSignIn.text.isNotEmpty()
            && binding.editTextUserNameSignIn.text.isNotEmpty()
            && binding.editTextConfirmPasswordSignIn.text.isNotEmpty()
            && binding.editTextConfirmPasswordSignIn.text.toString() == binding.editTextPasswordSignIn.text.toString()
        ) {
            inputEditTexts = true
        }
    }

    fun checkIsShowPasswords() {

        binding.btnShowPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextPasswordSignIn);
            }
            binding.editTextPasswordSignIn.setSelection(binding.editTextPasswordSignIn.getText().length);
        }

        binding.btnShowConfirmPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextConfirmPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextConfirmPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextConfirmPasswordSignIn);
            }
            binding.editTextConfirmPasswordSignIn.setSelection(binding.editTextConfirmPasswordSignIn.getText().length);
        }

    }

}