package com.example.memo_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.memo_2.databinding.ActivityMainBinding
import com.example.memo_2.room.MemoEntity
import com.example.memo_2.room.RoomHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var viewModel:MemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        viewModel.getData()

        viewModel.memoList.observe(this,{
            viewModel.memoList
            viewModel.getData()
            val memoAdapter = MemoAdapter(it)

            binding.rv.adapter = memoAdapter
            binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
        })

        with(binding) {
            saveBtn.setOnClickListener {
                val title = editTitle.text.toString()
                val content = editText.text.toString()
                if (title.isNotEmpty()) {
                    val datetime = System.currentTimeMillis()
                    val allMemo = MemoEntity(title, content, datetime)

                    viewModel.insertData(allMemo)
                    viewModel.memoList
                    viewModel.getData()

                    editText.setText("")
                    editTitle.setText("")

                }

            }
            addmemo.setOnClickListener {
                    viewModel.removeData()

            }

        }

    }

}