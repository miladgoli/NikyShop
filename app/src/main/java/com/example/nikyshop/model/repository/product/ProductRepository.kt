package com.example.nikyshop.model.repository.product

import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun addProduct(product: Product): Completable

    fun deleteProduct(product: Product): Completable

    fun getAllProducts(): Single<List<Product>>

    fun getProductWithCategory(category:String): Single<List<Product>>

}