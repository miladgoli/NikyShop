package com.example.nikyshop.model.repository.product

import com.example.nikyshop.model.database.product.ProductDao
import com.example.nikyshop.model.database.user.UserDao
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.entity.User
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryImp(val dao: ProductDao) : ProductRepository {

    override fun addProduct(product: Product): Completable {
        return dao.addProduct(product)
    }

    override fun deleteProduct(product: Product): Completable {
        return dao.deleteProduct(product)
    }

    override fun getAllProducts(): Single<List<Product>> {
        return dao.getAllProducts()
    }

    override fun getProductWithCategory(category: String): Single<List<Product>> {
        return dao.getProductWithCategory(category)
    }


}