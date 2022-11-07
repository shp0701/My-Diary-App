package com.example.mydiaryapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val with = parent.width
        val height = parent.height
        val flower00Drawable: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.flower00, null)
        val flower00With = flower00Drawable?.intrinsicWidth
        val flower00Height = flower00Drawable?.intrinsicHeight
        val left = with/2 - flower00With?.div(2) as Int
        val top = height/2 - flower00Height?.div(2) as Int

        canvas.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.flower00),
            left.toFloat(), top.toFloat(), null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val index = parent.getChildAdapterPosition(view) + 1
        if(index % 3 == 0){
            outRect.set(10,10,10,60)
        }else{
            outRect.set(10,10,10,60)
        }

        view.setBackgroundColor(Color.parseColor("#80f3f3f3"))
        ViewCompat.setElevation(view, 20.0f)
    }
}

