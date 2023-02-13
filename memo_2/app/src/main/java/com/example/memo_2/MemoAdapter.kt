package com.example.memo_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memo_2.databinding.MemoItemBinding
import java.text.SimpleDateFormat

// 여기선 연결만 시켜주는 역할 이므로 Room관련 내용은 하나도 쓰이지 않음

class MemoAdapter(val memoList : List<MemoEntity>) : RecyclerView.Adapter<MemoAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MemoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(memoList.get(position))
    }

    class Holder(val binding : MemoItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun setMemo(memo : MemoEntity){

            with(binding){

                titleArea.text = memo.title
                val date = SimpleDateFormat("yyyy/MM/dd hh:mm")
                dateArea.text = date.format(memo.datetime)

            }
        }
    }

}