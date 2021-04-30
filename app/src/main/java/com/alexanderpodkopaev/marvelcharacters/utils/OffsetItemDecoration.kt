package com.alexanderpodkopaev.marvelcharacters.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration(private val offset: Int) :RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = offset / 2
        outRect.right = offset / 2
        outRect.bottom = offset * 2
    }
}