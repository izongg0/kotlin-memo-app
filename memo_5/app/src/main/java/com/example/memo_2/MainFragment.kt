package com.example.memo_2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.memo_2.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {
    val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    lateinit var memoAdapter: MemoAdapter
    lateinit var helper: RoomHelper
    val memoList = mutableListOf<MemoEntity>() // 빈 리스트 변수만들기

    lateinit var mainActivity: MainActivity




    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        helper = Room.databaseBuilder(mainActivity, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries() // 공부할때만 쓴다.
            .build()

        memoList.addAll(helper.memoDao().getAll()) // db에 있는 모든 데이터를 빈 변수에 넣기

        memoAdapter = MemoAdapter(memoList) // 어댑터에 데이터 연결


        refreshAdapter()

        with(binding) {
            rv.adapter = memoAdapter
            rv.layoutManager = LinearLayoutManager(mainActivity)

        }

        memoAdapter.setOnItemClickListener(object : MemoAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: MemoEntity, pos : Int) {
                Log.d("wpqkf",data.datetime.toString())



                val passData = MainFragmentDirections.actionMainFragmentToEditFragment(data)
                Navigation.findNavController(binding.root)
                    .navigate(passData)
            }

        })

        binding.addmemo.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_addFragment)

        }

        return binding.root
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