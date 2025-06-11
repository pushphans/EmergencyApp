package com.example.college.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataClass(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,
    val number : String
)