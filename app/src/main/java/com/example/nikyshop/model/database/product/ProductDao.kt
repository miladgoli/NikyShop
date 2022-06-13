package com.example.nikyshop.model.database.product

import androidx.room.*
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single


//Database Interface Dao

@Dao
interface ProductDao {

    @Insert
    fun addProduct(product: Product): Completable

    @Delete
    fun deleteProduct(product: Product): Completable

    @Query("select * from tbl_product")
    fun getAllProducts(): Single<List<Product>>

    @Query("SELECT * FROM tbl_product WHERE category==:category")
    fun getProductWithCategory(category: String): Single<List<Product>>

}