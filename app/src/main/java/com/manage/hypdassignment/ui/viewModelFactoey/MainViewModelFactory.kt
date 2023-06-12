package com.manage.hypdassignment.ui.viewModelFactoey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manage.hypdassignment.data.CollectionRepository
import com.manage.hypdassignment.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val repo: CollectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw java.lang.Exception("Invalid ViewModel Class")
    }
}