package com.example.nikyshop.model.repository.user

import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun addUser(user: User): Completable

    fun updateUser(user: User): Completable

    fun getAllUsers(): Single<List<User>>

    fun getUserWithUserPass(username:String,password:String): Single<User?>

}