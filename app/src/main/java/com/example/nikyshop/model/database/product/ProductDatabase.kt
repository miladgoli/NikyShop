package com.example.nikyshop.model.database.product

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.entity.User

//Initilize baseDatabase
@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProducts(): ProductDao
}