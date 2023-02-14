package com.example.memo_2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 2)
abstract class RoomHelper : RoomDatabase(){
    //이 파일이 내가알던 helper 역할
    abstract fun memoDa0() : MemoDao

    companion object{
        @Volatile
        private var INSTANCE : RoomHelper?=null
        fun getDatabase(context : Context) : RoomHelper {
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext, RoomHelper::class.java,"text_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance

            }
        }
    }


}