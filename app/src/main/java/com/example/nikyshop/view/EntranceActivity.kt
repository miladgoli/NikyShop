package com.example.nikyshop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nikyshop.R
import com.example.nikyshop.databinding.ActivityEntranceBinding
import com.example.nikyshop.viewmodel.user.UserViewModel
import com.example.nikyshop.viewmodel.user.UserViewModelProvider

class EntranceActivity : AppCompatActivity() {

    lateinit var binding: ActivityEntranceBinding
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize viewmodel
        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(this)
        ).get(UserViewModel::class.java)

        //go to signin page
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerEntrance, SigninFragment())
            .commit()

    }
}