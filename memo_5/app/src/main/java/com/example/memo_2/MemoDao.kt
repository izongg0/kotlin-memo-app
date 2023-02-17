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

    @Query("DELETE FROM memo_tb WHERE no = :userId")
    fun deleteByUserId(userId: Long?)

    @Query("UPDATE memo_tb SET title =:title, content = :content, datetime = :datetime WHERE no =:no")
    fun updateMemo(no : Long? ,title : String, content : String, datetime : Long)

}