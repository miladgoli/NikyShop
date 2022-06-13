package com.example.nikyshop.viewmodel.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikyshop.model.entity.User
import com.example.nikyshop.model.repository.user.UserRepository
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(val repository: UserRepository) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()

    private val errorsMu: MutableLiveData<String> = MutableLiveData<String>()
    private val getUsersMu: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    private val getUserWithUserPassMu: MutableLiveData<User?> = MutableLiveData<User?>()

    fun addUser(user: User) {
        repository.addUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
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

    fun deleteUser(user: User) {
        repository.addUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
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

    fun updateUser(user: User) {
        repository.updateUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
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

    private fun getUsers() {
        repository.getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<User>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<User>) {
                    getUsersMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }

    private fun getUserWithUserPassword(username: String, password: String) {
        repository.getUserWithUserPass(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User?> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    getUserWithUserPassMu.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorsMu.postValue(e.toString())
                }

            })
    }


    fun getAllUsers(): LiveData<List<User>> {
        getUsers()
        return getUsersMu
    }

    fun getUserWithUserPass(username: String, password: String): LiveData<User?> {
        getUserWithUserPassword(username, password)
        return getUserWithUserPassMu
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