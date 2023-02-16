package com.example.memo_2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.memo_2.databinding.FragmentEditBinding
import com.example.memo_2.databinding.FragmentMainBinding
import java.text.SimpleDateFormat


class EditFragment : Fragment() {
    val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }

    val args : EditFragmentArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Log.d("qkedma",args.memo.title)


        with(binding) {

            val date = SimpleDateFormat("yyyy/MM/dd hh:mm")
            val datet = date.format(args.memo.datetime)

            dateview.setText(datet)
            editTitle.setText(args.memo.title)
            editText.setText(args.memo.content)
            backarrow.setOnClickListener {

                Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)
                }
            }



        // Inflate the layout for this fragment
        return binding.root
    }


}