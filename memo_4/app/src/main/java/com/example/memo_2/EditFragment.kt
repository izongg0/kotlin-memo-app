package com.example.memo_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.memo_2.databinding.FragmentEditBinding
import com.example.memo_2.databinding.FragmentMainBinding
import com.example.memo_2.room.MemoEntity


class EditFragment : Fragment() {

    val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }
    lateinit var viewModel:MemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        viewModel.getData()

        with(binding) {
            backarrow.setOnClickListener {
                val title = editTitle.text.toString()
                val content = editText.text.toString()
                if (title.isNotEmpty()) {
                    val datetime = System.currentTimeMillis()
                    val allMemo = MemoEntity(title, content, datetime)

                    viewModel.insertData(allMemo)
                    viewModel.memoList
                    viewModel.getData()


            Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)

                }

            }


        return binding.root
    }


}}