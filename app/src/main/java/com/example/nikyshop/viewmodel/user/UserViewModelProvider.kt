package com.example.nikyshop.viewmodel.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.nikyshop.model.database.user.UserDao
import com.example.nikyshop.model.database.user.UserDatabase
import com.example.nikyshop.model.repository.user.UserRepository
import com.example.nikyshop.model.repository.user.UserRepositoryImp

class UserViewModelProvider(val context:Context) :ViewModelProvider.Factory{

    lateinit var dao: UserDao
    lateinit var userRepository: UserRepository
    lateinit var database: UserDatabase

    fun initializeDatabase(){
        database=
            Room.databaseBuilder(context, UserDatabase::class.java, "db_user").build()
        dao=database.getUsers()
        userRepository=UserRepositoryImp(dao)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        initializeDatabase()
        return UserViewModel(userRepository) as T    }
}