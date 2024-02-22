/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */
package vinx.material.fastscroll

import android.graphics.Canvas
import android.view.MotionEvent

abstract class SimpleViewHelper : FastScroller.ViewHelper {
    private var mOnPreDrawListener: Runnable? = null
    private var mOnScrollChangedListener: Runnable? = null
    private var mOnTouchEventListener: Predicate<MotionEvent>? = null
    private var mListenerInterceptingTouchEvent = false
    override fun addOnPreDrawListener(onPreDraw: Runnable) {
        mOnPreDrawListener = onPreDraw
    }

    fun draw(canvas: Canvas) {
        if (mOnPreDrawListener != null) {
            mOnPreDrawListener!!.run()
        }
        superDraw(canvas)
    }

    override fun addOnScrollChangedListener(onScrollChanged: Runnable) {
        mOnScrollChangedListener = onScrollChanged
    }

    fun onScrollChanged(left: Int, top: Int, oldLeft: Int, oldTop: Int) {
        superOnScrollChanged(left, top, oldLeft, oldTop)
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener!!.run()
        }
    }

    fun addOnTouchEventListener(listener: Predicate<MotionEvent>?) {
        mOnTouchEventListener = listener
    }

    fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (mOnTouchEventListener != null && mOnTouchEventListener!!.test(event)) {
            val actionMasked = event.actionMasked
            if (actionMasked != MotionEvent.ACTION_UP
                && actionMasked != MotionEvent.ACTION_CANCEL
            ) {
                mListenerInterceptingTouchEvent = true
            }
            if (actionMasked != MotionEvent.ACTION_CANCEL) {
                val cancelEvent = MotionEvent.obtain(event)
                cancelEvent.action = MotionEvent.ACTION_CANCEL
                superOnInterceptTouchEvent(cancelEvent)
                cancelEvent.recycle()
            } else {
                superOnInterceptTouchEvent(event)
            }
            return true
        }
        return superOnInterceptTouchEvent(event)
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        if (mOnTouchEventListener != null) {
            if (mListenerInterceptingTouchEvent) {
                mOnTouchEventListener!!.test(event)
                val actionMasked = event.actionMasked
                if (actionMasked == MotionEvent.ACTION_UP
                    || actionMasked == MotionEvent.ACTION_CANCEL
                ) {
                    mListenerInterceptingTouchEvent = false
                }
                return true
            } else {
                val actionMasked = event.actionMasked
                if (actionMasked != MotionEvent.ACTION_DOWN && mOnTouchEventListener!!.test(event)) {
                    if (actionMasked != MotionEvent.ACTION_UP
                        && actionMasked != MotionEvent.ACTION_CANCEL
                    ) {
                        mListenerInterceptingTouchEvent = true
                    }
                    if (actionMasked != MotionEvent.ACTION_CANCEL) {
                        val cancelEvent = MotionEvent.obtain(event)
                        cancelEvent.action = MotionEvent.ACTION_CANCEL
                        superOnTouchEvent(cancelEvent)
                        cancelEvent.recycle()
                    } else {
                        superOnTouchEvent(event)
                    }
                    return true
                }
            }
        }
        return superOnTouchEvent(event)
    }

    override val scrollRange: Int
        get() = computeVerticalScrollRange()
    override val scrollOffset: Int
        get() = computeVerticalScrollOffset()

    override fun scrollTo(offset: Int) {
        scrollTo(scrollX, offset)
    }

    protected abstract fun superDraw(canvas: Canvas)
    protected abstract fun superOnScrollChanged(left: Int, top: Int, oldLeft: Int, oldTop: Int)
    protected abstract fun superOnInterceptTouchEvent(event: MotionEvent): Boolean
    protected abstract fun superOnTouchEvent(event: MotionEvent): Boolean
    protected abstract fun computeVerticalScrollRange(): Int
    protected abstract fun computeVerticalScrollOffset(): Int
    protected abstract val scrollX: Int

    protected abstract fun scrollTo(x: Int, y: Int)
}