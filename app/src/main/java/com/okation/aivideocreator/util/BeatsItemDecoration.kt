package com.okation.aivideocreator.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BeatsItemDecoration(val spacing : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            if(parent.getChildAdapterPosition(view) == 0){
                top = spacing
            }
            bottom = spacing
        }
    }
}