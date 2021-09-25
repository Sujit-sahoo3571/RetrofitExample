package com.futurebrains.retrofitexample.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.futurebrains.retrofitexample.data.repository.MainRepostiory
import com.futurebrains.retrofitexample.util.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepostiory: MainRepostiory) : ViewModel() {

    class MainViewModelFactory(private val mainRepostiory: MainRepostiory) :
        ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepostiory) as  T
        }

    }

    fun getUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepostiory.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}