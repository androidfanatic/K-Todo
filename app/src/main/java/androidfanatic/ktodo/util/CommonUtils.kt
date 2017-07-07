package androidfanatic.ktodo.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

fun RandomColorGenerator(key: Char): Int {
    val colors = listOf(
            0xffe57373,
            0xfff06292,
            0xffba68c8,
            0xff9575cd,
            0xff7986cb,
            0xff64b5f6,
            0xff4fc3f7,
            0xff4dd0e1,
            0xff4db6ac,
            0xff81c784,
            0xffaed581,
            0xffff8a65,
            0xffd4e157,
            0xffffd54f,
            0xffffb74d,
            0xffa1887f,
            0xff90a4ae
    )
    return colors[Math.abs(key.hashCode()) % colors.size].toInt()
}

class LinearRecyclerViewItemDecoration(val space: Int): RecyclerView.ItemDecoration(){

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = space
    }
}