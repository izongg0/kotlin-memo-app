package com.example.memo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "room_memo")
class MemoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    var id: Long? = null
    @ColumnInfo(name ="title")
    var title: String = ""
    @ColumnInfo(name ="content")
    var content: String = ""
    @ColumnInfo(name ="date")
    var datetime: Long =0


    constructor(title:String,content:String,datetime:Long){
        this.title = title//생성자
        this.content = content
        this.datetime = datetime
    }

}