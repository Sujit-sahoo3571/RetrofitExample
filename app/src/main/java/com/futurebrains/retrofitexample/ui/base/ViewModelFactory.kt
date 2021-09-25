package com.futurebrains.retrofitexample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.futurebrains.retrofitexample.data.api.ApiService
import com.futurebrains.retrofitexample.data.repository.MainRepostiory
import com.futurebrains.retrofitexample.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiService: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java) ){
            return MainViewModel(MainRepostiory(apiService)) as T
        }
        throw IllegalArgumentException("Unknown Class Name ")

    }
}