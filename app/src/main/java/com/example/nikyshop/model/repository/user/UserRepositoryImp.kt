package com.example.nikyshop.model.repository.user

import com.example.nikyshop.model.database.user.UserDao
import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImp(val dao: UserDao) : UserRepository {

    override fun addUser(user: User): Completable {
        return dao.addUser(user)
    }

    override fun updateUser(user: User): Completable {
        return dao.updateUser(user)
    }

    override fun getAllUsers(): Single<List<User>> {
        return dao.getAllUsers()
    }

    override fun getUserWithUserPass(username: String, password: String): Single<User?> {
        return dao.getUserWithUserPass(username,password)
    }

//    override fun getUserWithIsLogin(): Single<User?> {
//        return dao.getUserWithIsLogin()
//    }


}