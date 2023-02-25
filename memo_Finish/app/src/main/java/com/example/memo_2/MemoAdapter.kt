package com.example.memo_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo_2.databinding.MemoItemBinding
import java.text.SimpleDateFormat

class MemoAdapter : ListAdapter<MemoEntity, MemoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(var binding: MemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: MemoEntity) {
            binding.apply {
                titleArea.text = memo.title
                val date = SimpleDateFormat("yyyy/MM/dd hh:mm")
                dateArea.text = date.format(memo.datetime)

                val pos = adapterPosition
                if(pos!= RecyclerView.NO_POSITION)
                {
                    clickArea.setOnClickListener {
                        listener?.onItemClick(itemView,memo,pos)
                    }
                }

            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(v: View, data: MemoEntity, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MemoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // currentList: 해당 Adapter에 "submitList()"를 통해 삽입한 아이템 리스트
        holder.bind(currentList[position])
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<MemoEntity>() {
            override fun areItemsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
                return oldItem.no == newItem.no
            }

            override fun areContentsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}