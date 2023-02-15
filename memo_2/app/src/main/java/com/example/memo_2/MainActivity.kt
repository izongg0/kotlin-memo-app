package com.example.memo_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.memo_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var helper: RoomHelper
    lateinit var memoAdapter: MemoAdapter
    val memoList = mutableListOf<MemoEntity>() // 빈 리스트 변수만들기


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries() // 공부할때만 쓴다.
            .build()

        memoList.addAll(helper.memoDao().getAll()) // db에 있는 모든 데이터를 빈 변수에 넣기

        memoAdapter = MemoAdapter(memoList) // 어댑터에 데이터 연결


        refreshAdapter()

        with(binding) {
            rv.adapter = memoAdapter
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            saveBtn.setOnClickListener {
                val title = editTitle.text.toString()
                val content = editText.text.toString()
                if (title.isNotEmpty()) {
                    val datetime = System.currentTimeMillis()
                    val allMemo = MemoEntity(title, content, datetime)

                    insertMemo(allMemo)

                    editText.setText("")
                    editTitle.setText("")
                }

            }

        }


    }
    fun insertMemo(memo: MemoEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            helper.memoDao().insert(memo)
            refreshAdapter()
        }
    }


    fun refreshAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            memoList.clear()
            memoList.addAll(helper.memoDao().getAll())
            withContext(Dispatchers.Main) {
                memoAdapter.notifyDataSetChanged()
            }
        }

    }
}