package com.example.college.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VMFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(repository) as T
    }
}