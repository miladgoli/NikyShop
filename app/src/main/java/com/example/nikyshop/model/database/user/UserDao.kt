package com.example.nikyshop.model.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single

//Database Interface Dao

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User): Completable

    @Query("select * from tbl_user")
    fun getAllUsers(): Single<List<User>>

    @Update
    fun updateUser(user: User): Completable

    @Query("SELECT * FROM tbl_user WHERE userName == :usernameString and password == :password")
    fun getUserWithUserPass(usernameString: String,password:String): Single<User?>

//    @Query("SELECT * FROM tbl_user WHERE isLogin == :isLogin")
//    fun getUserWithIsLogin(isLogin:Boolean): Single<User?>
}