package com.example.memo_2

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.memo_2.room.RoomHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application):AndroidViewModel(application) {

//    lateinit var helper: RoomHelper

    val context = getApplication<Application>().applicationContext

    private val _memoList = MutableLiveData<List<MemoEntity>>() // 빈 리스트 변수만들기
    val memoList : LiveData<List<MemoEntity>>
    get() = _memoList
    val db = RoomHelper.getDatabase(context)

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _memoList.postValue(db.memoDa0().getAll())

    }

    fun insertData(memo : MemoEntity) = viewModelScope.launch(Dispatchers.IO) {
        //레포지 쓰기전
        db.memoDa0().insert(memo)
    }

    fun deleteById(id : Long?) = viewModelScope.launch(Dispatchers.IO) {
        //레포지 쓰기전
        db.memoDa0().deleteByUserId(id)
    }

    fun updateMemo(no : Long? ,title : String, content : String, datetime : Long) = viewModelScope.launch(Dispatchers.IO) {
        //레포지 쓰기전
        db.memoDa0().updateMemo(no,title,content,datetime)
    }


}