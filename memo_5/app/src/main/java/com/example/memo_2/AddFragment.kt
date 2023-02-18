package com.example.memo_2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.memo_2.databinding.FragmentAddBinding
import com.example.memo_2.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFragment : Fragment() {


    val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    lateinit var helper: RoomHelper

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


with(binding) {


    backarrow.setOnClickListener {

            Navigation.findNavController(binding.root).navigate(R.id.action_addFragment_to_mainFragment)

        }

    saveBtn.setOnClickListener {

        helper = Room.databaseBuilder(mainActivity, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries() // 공부할때만 쓴다.
            .build()

        val title = editTitle.text.toString()
        val content = editText.text.toString()
        if (title.isNotEmpty()) {
            val datetime = java.lang.System.currentTimeMillis()
            val allMemo = MemoEntity(null,title, content, datetime)

            insertMemo(allMemo)

            editText.setText("")
            editTitle.setText("")
            Navigation.findNavController(binding.root).navigate(R.id.action_addFragment_to_mainFragment)

        }

    }
    }







        return binding.root
    }

        fun insertMemo(memo: MemoEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            helper.memoDao().insert(memo)
//            refreshAdapter()
        }
    }

}