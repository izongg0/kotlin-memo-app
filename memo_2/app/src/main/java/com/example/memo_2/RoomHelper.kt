package com.example.memo_2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class RoomHelper : RoomDatabase() { //DAO 클래스들을 꺼내서 사용할 수 있도록 하는것임.
    abstract fun memoDao() : MemoDao
}