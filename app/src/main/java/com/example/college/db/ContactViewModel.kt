package com.example.college.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ContactViewModel (private val repository: ContactRepository) : ViewModel() {

    var isTrue = true
    private val _currentTime = MutableStateFlow<Float>(0F)
    val currentTime : StateFlow<Float> get() = _currentTime

    fun updateTime(time : Float){
        _currentTime.value = time
    }

    fun insertContact(dataClass: DataClass){
        viewModelScope.launch {
            repository.insertContact(dataClass)
        }
    }


    fun updateContact(dataClass: DataClass){
        viewModelScope.launch {
            repository.updateContact(dataClass)
        }
    }


    fun deleteContact(dataClass: DataClass){
        viewModelScope.launch {
            repository.deleteContact(dataClass)
        }
    }

    fun getAllContacts() : StateFlow<List<DataClass>>{
        return repository.getAllContacts().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

}