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

import android.view.View
import android.view.animation.Interpolator
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

class DefaultAnimationHelper(private val mView: View) : FastScroller.AnimationHelper {
    override var isScrollbarAutoHideEnabled = true
    private var mShowingScrollbar = true
    private var mShowingPopup = false
    override val scrollbarAutoHideDelayMillis = 1500
    override fun showScrollbar(trackView: View, thumbView: View) {
        if (mShowingScrollbar) {
            return
        }
        mShowingScrollbar = true
        trackView.animate().alpha(1f).translationX(0f).setDuration(SHOW_DURATION_MILLIS.toLong())
            .setInterpolator(SHOW_SCROLLBAR_INTERPOLATOR).start()
        thumbView.animate().alpha(1f).translationX(0f).setDuration(SHOW_DURATION_MILLIS.toLong())
            .setInterpolator(SHOW_SCROLLBAR_INTERPOLATOR).start()
    }

    override fun hideScrollbar(trackView: View, thumbView: View) {
        if (!mShowingScrollbar) {
            return
        }
        mShowingScrollbar = false
        val isLayoutRtl = mView.layoutDirection == View.LAYOUT_DIRECTION_RTL
        val width = trackView.width.coerceAtLeast(thumbView.width)
        val translationX: Float = if (isLayoutRtl) {
            (if (trackView.left == 0) -width else 0).toFloat()
        } else {
            (if (trackView.right == mView.width) width else 0).toFloat()
        }
        trackView.animate().alpha(0f).translationX(translationX)
            .setDuration(HIDE_DURATION_MILLIS.toLong()).setInterpolator(HIDE_SCROLLBAR_INTERPOLATOR)
            .start()
        thumbView.animate().alpha(0f).translationX(translationX)
            .setDuration(HIDE_DURATION_MILLIS.toLong()).setInterpolator(HIDE_SCROLLBAR_INTERPOLATOR)
            .start()
    }

    override fun showPopup(popupView: View) {
        if (mShowingPopup) {
            return
        }
        mShowingPopup = true
        popupView.animate().alpha(1f).setDuration(SHOW_DURATION_MILLIS.toLong()).start()
    }

    override fun hidePopup(popupView: View) {
        if (!mShowingPopup) {
            return
        }
        mShowingPopup = false
        popupView.animate().alpha(0f).setDuration(HIDE_DURATION_MILLIS.toLong()).start()
    }

    companion object {
        private const val SHOW_DURATION_MILLIS = 150
        private const val HIDE_DURATION_MILLIS = 200
        private val SHOW_SCROLLBAR_INTERPOLATOR: Interpolator = LinearOutSlowInInterpolator()
        private val HIDE_SCROLLBAR_INTERPOLATOR: Interpolator = FastOutLinearInInterpolator()
    }
}