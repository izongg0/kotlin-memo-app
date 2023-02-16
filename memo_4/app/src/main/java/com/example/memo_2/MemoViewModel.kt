package com.example.memo_2

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.memo_2.room.MemoEntity
import com.example.memo_2.room.RoomHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application):AndroidViewModel(application) {

//    lateinit var helper: RoomHelper

    val context = getApplication<Application>().applicationContext

    private val _memoList = MutableLiveData<List<MemoEntity>>() // 빈 리스트 변수만들기
    val memoList : LiveData<List<MemoEntity>>
    get() = _memoList

    private val _clickT = MutableLiveData<Boolean>()
    val clickT : LiveData<Boolean>
    get() = _clickT

    val db = RoomHelper.getDatabase(context)

    val memoAdapter = MemoAdapter(memoList)




    fun clickEv() = viewModelScope.launch(Dispatchers.IO) {
        _clickT.value= true
        memoAdapter.setOnItemClickListener(object : MemoAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: MemoEntity, pos: Int) {
                _clickT.value = false
                Log.d("wpqkf",_clickT.value.toString())
            }
        })

    }
    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _memoList.postValue(db.memoDa0().getAll())

    }

    fun insertData(memo : MemoEntity) = viewModelScope.launch(Dispatchers.IO) {
        //레포지 쓰기전
        db.memoDa0().insert(memo)
    }

    fun removeData() = viewModelScope.launch(Dispatchers.IO) {
        //레포지 쓰기전
        db.memoDa0().deleteAllData()

    }





}