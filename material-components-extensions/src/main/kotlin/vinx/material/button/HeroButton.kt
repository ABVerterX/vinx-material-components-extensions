package vinx.material.button

import android.R.attr.button
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.internal.ViewUtils
import vinx.material.R
import java.lang.reflect.Field

@SuppressLint("PrivateApi")
class HeroButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialButtonTextStyle,
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        val backgroundDrawable = background as RippleDrawable
        val state = backgroundDrawable.constantState
        try {
            val colorField: Field = state!!.javaClass.getDeclaredField("mColor")
            colorField.isAccessible = true
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        val typedValue =
            context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackgroundBorderless))
        val resId = typedValue.getResourceId(0, 0)
        typedValue.recycle()
        val drawable = ResourcesCompat.getDrawable(
            context.resources, resId, context.theme
        ) as RippleDrawable
        drawable.setColor(rippleColor as ColorStateList)
        setBackgroundDrawable(drawable)

        insetTop = 0
        insetBottom = 0
        iconGravity = ICON_GRAVITY_TEXT_TOP
        iconSize = ViewUtils.dpToPx(context, 24).toInt()
        iconPadding = ViewUtils.dpToPx(context, 8).toInt()
        val padding = ViewUtils.dpToPx(context, 16).toInt()
        setPadding(padding, padding, padding, padding)
//        gravity = Gravity.TOP or Gravity.CENTER
    }

}