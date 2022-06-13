package com.example.nikyshop.model.database.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nikyshop.model.entity.User

//Initilize baseDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUsers(): UserDao
}