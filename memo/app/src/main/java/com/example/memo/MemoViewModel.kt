package com.example.memo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    val db = MemoDB.getDatabase(context)

    private val _memoList = MutableLiveData<List<MemoEntity>>()
    val memoList : LiveData<List<MemoEntity>>
    get() = _memoList


    val datetime = System.currentTimeMillis()

    fun getData() = viewModelScope.launch(Dispatchers.IO){
        _memoList.postValue(db.memoDao().getAllData())

    }

    fun insertData(title : String, content:String, date:Long) = viewModelScope.launch(Dispatchers.IO){

        db.memoDao().insert(MemoEntity(title,content,date))
    }




}