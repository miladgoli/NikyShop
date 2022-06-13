package com.example.nikyshop.viewmodel.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.entity.User
import com.example.nikyshop.model.repository.product.ProductRepository
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductViewModel(val repository: ProductRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val errorsMu: MutableLiveData<String> = MutableLiveData<String>()
    private val getProductsMu: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    private val getProductWithCategoryMu: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()

    private fun getAllProducts(){
        repository.getAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    getProductsMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun addProduct(product: Product){
        repository.addProduct(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun deleteProduct(product: Product){
        repository.deleteProduct(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }


    private fun getProductsWithCategory(category:String){
        repository.getProductWithCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                   getProductWithCategoryMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    fun getProducts(): LiveData<List<Product>> {
        getAllProducts()
        return getProductsMu
    }

    fun getProductWithCategory(category:String): LiveData<List<Product>> {
        getProductsWithCategory(category)
        return getProductWithCategoryMu
    }

    fun getErrors(): LiveData<String> {
        return errorsMu
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}