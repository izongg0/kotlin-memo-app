package com.example.memo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    lateinit var mainActivity: MainActivity

    lateinit var viewModel:MemoViewModel


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
        // Inflate the layout for this fragment



        binding = FragmentMainBinding.inflate(layoutInflater)
        val view =binding.root

        val rv = binding.rv

        val memoAdapter = MemoAdapter(memoList)
        rv.adapter = memoAdapter
        rv.layoutManager = LinearLayoutManager(mainActivity)

        viewModel.memoList.observe(mainActivity,{
            val memoAdapter = MemoAdapter(it)
            rv.adapter = memoAdapter
            rv.layoutManager = LinearLayoutManager(mainActivity)

        })
        initView()

        return view
    }

    private fun initView() = with(binding){
        binding.addmemo.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_editFragment)
        }
    }


}