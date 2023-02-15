package com.example.memo_2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.memo_2.databinding.MemoItemBinding
import com.example.memo_2.room.MemoEntity
import java.text.SimpleDateFormat

// 여기선 연결만 시켜주는 역할 이므로 Room관련 내용은 하나도 쓰이지 않음

class MemoAdapter(val memoList : LiveData<List<MemoEntity>>) : RecyclerView.Adapter<MemoAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MemoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return memoList.value?.size!!
    }




    override fun onBindViewHolder(holder: Holder, position: Int) {


//        holder.setMemo(memoList[position])
        memoList.value?.get(position)?.let {
            holder.setMemo(it)

        }
    }


    interface OnItemClickListener{
        fun onItemClick(v:View, data: MemoEntity, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }



    inner class Holder(val binding : MemoItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun setMemo(memo : MemoEntity){

            with(binding){
                titleArea.text = memo.title
                val date = SimpleDateFormat("yyyy/MM/dd hh:mm")
                dateArea.text = date.format(memo.datetime)
                val pos = adapterPosition
                if(pos!= RecyclerView.NO_POSITION)
                {
                    titleArea.setOnClickListener {
                        listener?.onItemClick(itemView,memo,pos)
                    }
                }
            }
        }


    }

}