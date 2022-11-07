package com.example.mydiaryapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiaryapplication.databinding.ItemMainBinding

class CustomAdapter(val dataList: MutableList<DataVO>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        val customViewHolder = CustomViewHolder(binding)

        //아이템 항목에 이벤트 설정
        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            Toast.makeText(parent.context, "날짜:${dataVO.date}, 내용:${dataVO.contents}", Toast.LENGTH_SHORT).show()
        }
        //아이템 항목에 롱클릭했을때 삭제
        customViewHolder.itemView.setOnLongClickListener{
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            (parent.context as MainActivity).oneFragment.refreshRecyclerViewDrop(dataVO)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataVO = dataList.get(position)
        binding.ivPicture.setImageResource(dataVO.picture)
        binding.tvName.text = dataVO.date
        binding.tvAge.text = dataVO.contents
    }

    class CustomViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
}