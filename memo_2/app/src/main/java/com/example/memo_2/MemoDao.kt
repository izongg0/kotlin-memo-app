package com.example.memo_2

import androidx.room.*

@Dao
interface MemoDao {
    @Query("select * from memo_tb")
    fun getAll() : List<MemoEntity> // 전체조회

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo:MemoEntity)

    @Delete
    fun delete(memo:MemoEntity)

//    @Query("DELETE FROM room_memo WHERE no = :userId")
//    fun deleteByUserId(userId: Long)


}