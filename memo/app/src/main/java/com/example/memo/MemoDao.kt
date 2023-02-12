package com.example.memo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemoDao {

    @Query("SELECT * FROM room_memo")
    fun getAllData() : List<MemoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(text : MemoEntity)

    @Query("DELETE FROM room_memo")
    fun deleteAllData()

}