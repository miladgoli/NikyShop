package com.example.nikyshop.viewmodel.product

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.nikyshop.model.database.product.ProductDao
import com.example.nikyshop.model.database.product.ProductDatabase
import com.example.nikyshop.model.repository.product.ProductRepository
import com.example.nikyshop.model.repository.product.ProductRepositoryImp

class ProductViewModelProvider(val context: Context) : ViewModelProvider.Factory {

    lateinit var dao: ProductDao
    lateinit var productRepository: ProductRepository
    lateinit var database: ProductDatabase

    fun initializeDatabase() {
        database =
            Room.databaseBuilder(context, ProductDatabase::class.java, "db_product").build()
        dao = database.getProducts()
        productRepository = ProductRepositoryImp(dao)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        initializeDatabase()
        return ProductViewModel(productRepository) as T
    }
}