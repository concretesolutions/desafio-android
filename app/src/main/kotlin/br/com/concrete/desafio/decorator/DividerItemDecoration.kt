package br.com.concrete.desafio.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concrete.desafio.R

class DividerItemDecoration(context: Context, @DimenRes startMargin: Int) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = null
    private var mVerticalSpaceHeight: Int = 0
    private var startMargin: Int = 0

    init {
        this.startMargin = context.resources.getDimensionPixelOffset(startMargin)
        mDivider = context.getDrawable(R.drawable.line_divider)
        mVerticalSpaceHeight = context.resources.getDimensionPixelOffset(R.dimen.divider_height)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        val left = parent.paddingLeft + startMargin
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0..childCount - 1 - 1) {

            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight

            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State?) {
        if (parent.getChildAdapterPosition(view) != parent.adapter.itemCount - 1) {
            outRect.bottom = mVerticalSpaceHeight
        }
    }

}