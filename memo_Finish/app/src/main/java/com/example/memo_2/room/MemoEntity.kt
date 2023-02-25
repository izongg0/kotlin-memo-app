package com.example.memo_2

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "memo_tb")
@Parcelize
data class MemoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null,
    @ColumnInfo
    var title: String = "",
    @ColumnInfo
    var content: String = "",
    @ColumnInfo
    var datetime: Long =0,
) : Parcelable



