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

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.util.Consumer
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import me.zhanghai.android.fastscroll.FastScrollNestedScrollView
import me.zhanghai.android.fastscroll.FastScrollScrollView
import vinx.material.R

class FastScrollerBuilder(private val viewGroup: ViewGroup) {
    private var viewHelper: FastScroller.ViewHelper? = null
    private var popupTextProvider: PopupTextProvider? = null
    private var padding: Rect? = null
    private var trackDrawable: Drawable? = null
    private var thumbDrawable: Drawable? = null
    private var popupStyle: Consumer<TextView>? = null
    private var animationHelper: FastScroller.AnimationHelper? = null

    init {
        useDefaultStyle()
    }

    fun setViewHelper(viewHelper: FastScroller.ViewHelper?): FastScrollerBuilder {
        this.viewHelper = viewHelper
        return this
    }

    fun setPopupTextProvider(popupTextProvider: PopupTextProvider?): FastScrollerBuilder {
        this.popupTextProvider = popupTextProvider
        return this
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int): FastScrollerBuilder {
        if (padding == null) {
            padding = Rect()
        }
        padding!![left, top, right] = bottom
        return this
    }

    fun setPadding(padding: Rect?): FastScrollerBuilder {
        if (padding != null) {
            if (this.padding == null) {
                this.padding = Rect()
            }
            this.padding!!.set(padding)
        } else {
            this.padding = null
        }
        return this
    }

    fun setTrackDrawable(trackDrawable: Drawable): FastScrollerBuilder {
        this.trackDrawable = trackDrawable
        return this
    }

    fun setThumbDrawable(thumbDrawable: Drawable): FastScrollerBuilder {
        this.thumbDrawable = thumbDrawable
        return this
    }

    fun setPopupStyle(popupStyle: Consumer<TextView>): FastScrollerBuilder {
        this.popupStyle = popupStyle
        return this
    }

    fun useDefaultStyle(): FastScrollerBuilder {
        val context = viewGroup.context
        trackDrawable = Utils.getGradientDrawableWithTintAttr(
            R.drawable.afs_track,
            android.R.attr.colorControlNormal, context
        )!!
        thumbDrawable = Utils.getGradientDrawableWithTintAttr(
            R.drawable.afs_thumb,
            android.R.attr.colorControlActivated, context
        )!!
        popupStyle = PopupStyles.DEFAULT
        return this
    }

    fun useMaterial2Style(): FastScrollerBuilder {
        val context = viewGroup.context
        trackDrawable = Utils.getGradientDrawableWithTintAttr(
            R.drawable.afs_md2_track,
            android.R.attr.colorControlNormal, context
        )!!
        thumbDrawable = Utils.getGradientDrawableWithTintAttr(
            R.drawable.afs_md2_thumb,
            android.R.attr.colorControlActivated, context
        )!!
        popupStyle = PopupStyles.MD2
        return this
    }

    fun setAnimationHelper(animationHelper: FastScroller.AnimationHelper?) {
        this.animationHelper = animationHelper
    }

    fun disableScrollbarAutoHide() {
        val animationHelper = DefaultAnimationHelper(viewGroup)
        animationHelper.isScrollbarAutoHideEnabled = false
        this.animationHelper = animationHelper
    }

    fun build(): FastScroller {
        return FastScroller(
            viewGroup,
            orCreateViewHelper, padding, trackDrawable!!,
            thumbDrawable!!, popupStyle,
            orCreateAnimationHelper
        )
    }

    private val orCreateViewHelper: FastScroller.ViewHelper
        get() {
            if (viewHelper != null) {
                return viewHelper as FastScroller.ViewHelper
            }
            when (viewGroup) {
                is ViewHelperProvider -> {
                    return (viewGroup as ViewHelperProvider).viewHelper
                }

                is RecyclerView -> {
                    return RecyclerViewHelper(viewGroup, popupTextProvider)
                }

                is NestedScrollView -> {
                    throw UnsupportedOperationException(
                        "Please use "
                                + FastScrollNestedScrollView::class.java.simpleName + " instead of "
                                + NestedScrollView::class.java.simpleName + "for fast scroll"
                    )
                }

                is ScrollView -> {
                    throw UnsupportedOperationException(
                        ("Please use "
                                + FastScrollScrollView::class.java.simpleName + " instead of "
                                + ScrollView::class.java.simpleName + "for fast scroll")
                    )
                }

                is WebView -> {
                    throw UnsupportedOperationException(
                        ("Please use "
                                + FastScrollWebView::class.java.simpleName + " instead of "
                                + WebView::class.java.simpleName + "for fast scroll")
                    )
                }

                else -> {
                    throw UnsupportedOperationException(
                        (viewGroup.javaClass.simpleName
                                + " is not supported for fast scroll")
                    )
                }
            }
        }
    private val orCreateAnimationHelper: FastScroller.AnimationHelper
        get() {
            return animationHelper ?: DefaultAnimationHelper(viewGroup)
        }
}