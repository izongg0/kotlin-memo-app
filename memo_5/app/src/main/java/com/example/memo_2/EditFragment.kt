package com.example.memo_2

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.memo_2.databinding.FragmentEditBinding
import com.example.memo_2.databinding.FragmentMainBinding
import java.text.SimpleDateFormat


class EditFragment : Fragment() {
    val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }

    val args : EditFragmentArgs by navArgs()
    lateinit var helper: RoomHelper

    lateinit var mainActivity: MainActivity




    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        helper = Room.databaseBuilder(mainActivity, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries()
            .build()

                val callback : OnBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                    view?.let{
                        val datetime = java.lang.System.currentTimeMillis()

                        helper.memoDao().updateMemo(args.memo.no,binding.editTitle.text.toString(),binding.editText.text.toString(),datetime)

                        Toast.makeText(mainActivity, "수정되었습니다.", Toast.LENGTH_SHORT).show()



                        Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)                    }
            }
        }
            requireActivity().onBackPressedDispatcher.addCallback(this,callback)

    }

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

            val datetime = java.lang.System.currentTimeMillis()

            backarrow.setOnClickListener {

                helper.memoDao().updateMemo(args.memo.no,editTitle.text.toString(),editText.text.toString(),datetime)

                Toast.makeText(mainActivity, "수정되었습니다.", Toast.LENGTH_SHORT).show()



                Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)
                }

            garbage.setOnClickListener {

                deleteUser()
//                helper.memoDao().deleteByUserId(args.memo.no)
//
//                Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)


            }
            }



        // Inflate the layout for this fragment
        return binding.root
    }
    private fun deleteUser(){ //delteIcon이 나오면 Dialog를 띄워서 물어보겠습니다.
        val builder = AlertDialog.Builder(mainActivity)

        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setPositiveButton("Yes"){ _, _ ->
            helper.memoDao().deleteByUserId(args.memo.no)
            Navigation.findNavController(binding.root).navigate(R.id.action_editFragment_to_mainFragment)
        }

        //dialog의 UI세팅입니다.
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure to delete ? ")

        //dialog가 UI에 보여집니다.
        builder.create().show()
    }

}

