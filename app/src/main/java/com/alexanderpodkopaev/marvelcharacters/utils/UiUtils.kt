package com.alexanderpodkopaev.marvelcharacters.utils

import android.content.Context
import android.util.DisplayMetrics

object UiUtils {
    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels
        return (screenWidthDp / columnWidthDp).toInt()
    }
}
