package com.example.memo_2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.memo_2.MainActivity
import com.example.memo_2.MemoEntity
import com.example.memo_2.MemoViewModel
import com.example.memo_2.R
import com.example.memo_2.databinding.FragmentAddBinding


class AddFragment : Fragment() {


    val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    lateinit var viewModel: MemoViewModel
    lateinit var mainActivity: MainActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)

        with(binding) {

            backarrow.setOnClickListener {

                Navigation.findNavController(binding.root).navigate(R.id.action_addFragment_to_mainFragment)

            }

            saveBtn.setOnClickListener {

                val title = editTitle.text.toString()
                val content = editText.text.toString()
                if (title.isNotEmpty()) {
                    val datetime = java.lang.System.currentTimeMillis()
                    val allMemo = MemoEntity(null,title, content, datetime)

                    viewModel.insertData(allMemo)

                    editText.setText("")
                    editTitle.setText("")
                    Navigation.findNavController(binding.root).navigate(R.id.action_addFragment_to_mainFragment)
                }

            }
        }


        return binding.root
    }



}