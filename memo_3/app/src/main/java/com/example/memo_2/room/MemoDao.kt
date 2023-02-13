package com.example.memo_2.room

import androidx.room.*

@Dao
interface MemoDao {
    @Query("select * from memo_tb")
    fun getAll() : List<MemoEntity> // 전체조회

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(memo: MemoEntity)

    @Delete
    fun delete(memo: MemoEntity)

    @Query("DELETE FROM memo_tb")
    fun deleteAllData()

//    @Query("DELETE FROM room_memo WHERE no = :userId")
//    fun deleteByUserId(userId: Long)


}