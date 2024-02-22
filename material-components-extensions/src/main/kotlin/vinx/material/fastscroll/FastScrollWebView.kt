/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vinx.material.fastscroll

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

@SuppressLint("MissingSuperCall")
class FastScrollWebView : WebView, ViewHelperProvider {
    private val mViewHelper: ViewHelper = ViewHelper()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context, attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(
        context: Context, attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        isVerticalScrollBarEnabled = false
        isScrollContainer = true
    }

    override val viewHelper: FastScroller.ViewHelper
        get() = mViewHelper

    override fun draw(canvas: Canvas) {
        mViewHelper.draw(canvas)
    }

    override fun onScrollChanged(left: Int, top: Int, oldLeft: Int, oldTop: Int) {
        mViewHelper.onScrollChanged(left, top, oldLeft, oldTop)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return mViewHelper.onInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return mViewHelper.onTouchEvent(event)
    }

    private inner class ViewHelper : SimpleViewHelper() {
        override fun superDraw(canvas: Canvas) {
            super@FastScrollWebView.draw(canvas)
        }

        override fun superOnScrollChanged(left: Int, top: Int, oldLeft: Int, oldTop: Int) {
            super@FastScrollWebView.onScrollChanged(left, top, oldLeft, oldTop)
        }

        override fun superOnInterceptTouchEvent(event: MotionEvent): Boolean {
            return super@FastScrollWebView.onInterceptTouchEvent(event)
        }

        override fun superOnTouchEvent(event: MotionEvent): Boolean {
            return super@FastScrollWebView.onTouchEvent(event)
        }

        override fun computeVerticalScrollRange(): Int {
            return this@FastScrollWebView.computeVerticalScrollRange()
        }

        override fun computeVerticalScrollOffset(): Int {
            return this@FastScrollWebView.computeVerticalScrollOffset()
        }

        override val scrollX: Int
            get() = this@FastScrollWebView.scrollX

        override fun addOnTouchEventListener(onTouchEvent: Predicate<MotionEvent>) {

        }

        override fun scrollTo(x: Int, y: Int) {
            this@FastScrollWebView.scrollTo(x, y)
        }
    }
}