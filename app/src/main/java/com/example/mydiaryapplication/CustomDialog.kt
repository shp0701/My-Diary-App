package com.example.mydiaryapplication

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.mydiaryapplication.databinding.DialogCustomBinding
import com.example.mydiaryapplication.databinding.FragmentOneBinding

class CustomDialog(val context: Context, val oneFragment: FragmentOneBinding) {
    val dialog = Dialog(context)

    fun showDialog() {
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        //취소버튼, ok버튼 이벤트처리
        binding.dialogBtnCancle.setOnClickListener {
            dialog.dismiss()
        }
        binding.dialogBtnOk.setOnClickListener {
            val date = binding.dialogTvDate.text.toString()
            val contents = binding.dialogTvMemo.text.toString()
            val number = (Math.random() * 100).toInt()
            var dataVO: DataVO
            if (number % 2 == 0) {
                dataVO = DataVO(date, contents, R.drawable.diary_five)
            } else {
                dataVO = DataVO(date, contents, R.drawable.diary_five)
            }
            (context as MainActivity).oneFragment.refreshRecycleViewAdd(dataVO)
            dialog.dismiss()
        }
    }
}

