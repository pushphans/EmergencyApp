package com.example.college.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


class ContactRepository (private val database: ContactDatabase) {



    suspend fun insertContact(dataClass: DataClass){
        database.getDao().insertContact(dataClass)
    }

    suspend fun updateContact(dataClass: DataClass){
        database.getDao().updateContact(dataClass)
    }

    suspend fun deleteContact(dataClass: DataClass){
        database.getDao().deleteContact(dataClass)
    }

    fun getAllContacts() : Flow<List<DataClass>> {
        return database.getDao().getAllContacts()

    }

}