package com.example.memo_2.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_tb")
class MemoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var title: String = ""
    @ColumnInfo
    var content: String = ""
    @ColumnInfo
    var datetime: Long =0


    constructor(title:String,content:String,datetime:Long){ //생성자
        this.title = title
        this.content = content
        this.datetime = datetime

    }

}