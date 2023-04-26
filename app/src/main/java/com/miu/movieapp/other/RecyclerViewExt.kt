package com.miu.movieapp.other

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Enable scrolling for the RecyclerView as long as it can scroll in the direction.
 * Let the ViewPager scroll and change its page if the end of the RecyclerView is reached.
 */
fun RecyclerView.setupHorizontalScrollWithViewPager() {
    addOnItemTouchListener(
        object : RecyclerView.OnItemTouchListener {
            private var startX = 0f

            override fun onInterceptTouchEvent(
                recyclerView: RecyclerView,
                event: MotionEvent
            ): Boolean =
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> startX = event.x
                    MotionEvent.ACTION_MOVE -> {
                        val isScrollingRight = event.x < startX
                        val scrollItemsToRight = isScrollingRight && recyclerView.canScrollRight
                        val scrollItemsToLeft = !isScrollingRight && recyclerView.canScrollLeft
                        val disallowIntercept = scrollItemsToRight || scrollItemsToLeft
                        recyclerView.parent.requestDisallowInterceptTouchEvent(disallowIntercept)
                    }

                    MotionEvent.ACTION_UP -> startX = 0f
                    else -> Unit
                }.let { false }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit
        }
    )
}

private val RecyclerView.canScrollRight: Boolean
    get() = canScrollHorizontally(SCROLL_DIRECTION_RIGHT)
private val RecyclerView.canScrollLeft: Boolean
    get() = canScrollHorizontally(SCROLL_DIRECTION_LEFT)
private const val SCROLL_DIRECTION_RIGHT = 1
private const val SCROLL_DIRECTION_LEFT = -1