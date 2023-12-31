package com.okation.aivideocreator.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PromptsItemDecoration(private val spanCount : Int,private val spacing : Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = spacing/2
        outRect.bottom = spacing/2
        outRect.left = spacing/2
        outRect.right = spacing
    }
}