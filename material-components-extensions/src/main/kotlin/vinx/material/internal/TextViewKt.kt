package vinx.material.internal

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat

val TextView.firstBaselineToTopHeightCompat: Int
    get() = TextViewCompat.getFirstBaselineToTopHeight(this)

val TextView.lastBaselineToBottomHeightCompat: Int
    get() = TextViewCompat.getLastBaselineToBottomHeight(this)

var TextView.textWithVisibility: CharSequence?
    get() = this.text
    set(value) {
        this.visibility = if (value == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
        this.text = value
    }