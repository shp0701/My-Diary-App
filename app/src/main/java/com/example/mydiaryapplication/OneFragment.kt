package com.example.mydiaryapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiaryapplication.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    var dataList = mutableListOf<DataVO>()
    lateinit var customAdapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        //이벤트 처리부분
        //리사클러뷰에 데이터리스트 제작한다

        for (i in 1..10){
            if(i%2 ==0) {
                dataList.add(DataVO("22년/11월/${i}", "memo", R.drawable.diary_five))
            }else{
                dataList.add(DataVO("22년/11월/${i}", "memo", R.drawable.diary_five))
            }
        }
        //리사클러뷰에 보여줄 레이아웃결정
        val LinearLayoutManager = LinearLayoutManager(container?.context)
        //리사클 러뷰에 제공할 어뎁터
        customAdapter = CustomAdapter(dataList)
        //리사클러뷰에 연결
        binding.f1Recyclerview.layoutManager=LinearLayoutManager
        binding.f1Recyclerview.adapter = customAdapter

        //데코레이션 여기서 연결 (과제)
        binding.f1Recyclerview.addItemDecoration(MyDecoration(binding.root.context))

        //플로팅 탭을 누르면 사용자 다이얼로그 보여줘서 입력한 데이터를 dataList에 추가한다.
        binding.f1fab.setOnClickListener {
            //사용자 다이얼로그 창을 만들어서 dataList 추가해야한다
            val dialog = CustomDialog(binding.root.context,binding)
            dialog.showDialog()

        }

        return binding.root
    }

    fun refreshRecycleViewAdd(dataVO: DataVO){}

    fun refreshRecycleViewDrop(dataVO: DataVO){
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()
    }

    fun refreshRecyclerViewAdd(dataVO: DataVO) {
        Toast.makeText(binding.root.context, "${dataVO}", Toast.LENGTH_SHORT).show()
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()

    }

    fun refreshRecyclerViewDrop(dataVO: DataVO) {
        Toast.makeText(binding.root.context, "${dataVO}", Toast.LENGTH_SHORT).show()
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()

    }

}

