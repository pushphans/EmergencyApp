package com.example.college.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface Dao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(dataClass: DataClass)

    @Update
    suspend fun updateContact(dataClass: DataClass)

    @Delete
    suspend fun deleteContact(dataClass: DataClass)

    @Query("SELECT * FROM DataClass ORDER BY id DESC")
    fun getAllContacts() : Flow<List<DataClass>>
}