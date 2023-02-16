package com.example.memo_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.sqlite.db.SupportSQLiteCompat
import com.example.memo_2.databinding.ActivityMainBinding
import com.example.memo_2.databinding.FragmentMainBinding
import com.example.memo_2.room.MemoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    lateinit var viewModel:MemoViewModel


    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        viewModel.getData()


        val memoAdapter = MemoAdapter(viewModel.memoList)
        binding.rv.adapter = memoAdapter
        binding.rv.layoutManager = LinearLayoutManager(mainActivity)

//        memoAdapter.setOnItemClickListener(object : MemoAdapter.OnItemClickListener{
//            override fun onItemClick(v: View, data: MemoEntity, pos : Int) {
//
//                Log.d("wpqkf","aaaa")
//            }
//
//        })

        viewModel.memoList.observe(viewLifecycleOwner,{
            viewModel.memoList
            viewModel.getData()
            memoAdapter.notifyDataSetChanged()
        })

        viewModel.clickT.observe(viewLifecycleOwner,{
            viewModel.clickEv()

        })




        binding.addmemo.setOnClickListener {

            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_editFragment)

        }

        return binding.root
    }


}