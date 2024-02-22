package vinx.material.radiobutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.CompoundButtonCompat

class MaterialRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.radioButtonStyle
) : com.google.android.material.radiobutton.MaterialRadioButton(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
//         Horizontally center the button drawable and ripple when there's no text.
        if (TextUtils.isEmpty(text)) {
            val drawable: Drawable? = CompoundButtonCompat.getButtonDrawable(this)
            if (drawable != null) {
                val dx = (width - drawable.intrinsicWidth) / 2
                val saveCount: Int = canvas.save()
                canvas.translate(dx.toFloat(), 0f)
                super.onDraw(canvas)
                canvas.restoreToCount(saveCount)
                if (background != null) {
                    val bounds: Rect = drawable.bounds
                    DrawableCompat.setHotspotBounds(
                        background, bounds.left + dx, bounds.top, bounds.right + dx, bounds.bottom
                    )
                }
                return
            }
        }
        super.onDraw(canvas)
    }
}