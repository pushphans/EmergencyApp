package com.example.college.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataClass::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getDao() : Dao


    companion object{
        @Volatile
        private var instance : ContactDatabase? = null

        fun getDatabase(context: Context) : ContactDatabase{
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, "database").build()
                    }
                }
            }
            return instance!!
        }
    }
}