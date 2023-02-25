package com.example.memo_2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo_2.*
import com.example.memo_2.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    lateinit var viewModel: MemoViewModel
        lateinit var memoAdapter: MemoAdapter

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
        viewModel.getData()


        memoAdapter = MemoAdapter()
        binding.rv.adapter = memoAdapter
        binding.rv.layoutManager = LinearLayoutManager(mainActivity)

        viewModel.memoList.observe(viewLifecycleOwner,{
            viewModel.getData()
            memoAdapter.submitList(it)
        })

        memoAdapter.setOnItemClickListener(object : MemoAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: MemoEntity, pos : Int) {

                val passData = MainFragmentDirections.actionMainFragmentToEditFragment(data)
                Navigation.findNavController(binding.root)
                    .navigate(passData)
            }

        })





        binding.addmemo.setOnClickListener {

            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_addFragment)

        }

        return binding.root
    }


}