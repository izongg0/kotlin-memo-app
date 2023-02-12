package com.example.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.databinding.MemoItemBinding
import java.text.SimpleDateFormat

class MemoAdapter(private val memoList : List<MemoEntity>) : RecyclerView.Adapter<MemoAdapter.Holder>() {

    class Holder(val binding: MemoItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun setMemo(memo:MemoEntity){
            with(binding){
                titleitem.text = memo.content
                val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
                dateitem.text = sdf.format(memo.datetime)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(memoList.get(position))
    }
}