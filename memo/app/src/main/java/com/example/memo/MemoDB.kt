package com.example.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDB : RoomDatabase(){
    //이 파일이 내가알던 helper 역할
    abstract fun memoDao() : MemoDao

    companion object{
        @Volatile
        private var INSTANCE : MemoDB?=null
        fun getDatabase(context : Context) : MemoDB {
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext, MemoDB::class.java,"memo_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance

            }
        }
    }


}