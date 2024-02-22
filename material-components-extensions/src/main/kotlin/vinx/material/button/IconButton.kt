package vinx.material.button

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.Px
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButton.OnCheckedChangeListener
import com.google.android.material.shape.ShapeAppearanceModel
import vinx.material.R

private typealias STYLE = com.google.android.material.R.style

@SuppressLint("PrivateResource")
class IconButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, 0) {

    private var button: MaterialButton

    init {
        val layout: Int = when (defStyleAttr) {
            STYLE.Widget_MaterialComponents_Button_Icon -> R.layout.mdc_iconbutton
            STYLE.Widget_MaterialComponents_Button_UnelevatedButton_Icon -> R.layout.mdc_iconbutton_filled
            STYLE.Widget_MaterialComponents_Button_TextButton_Icon -> R.layout.mdc_iconbutton_standard
            STYLE.Widget_MaterialComponents_Button_OutlinedButton_Icon -> R.layout.mdc_iconbutton_outlined

            STYLE.Widget_Material3_Button_Icon -> R.layout.m3_iconbutton_filled
            STYLE.Widget_Material3_Button_TonalButton_Icon -> R.layout.m3_iconbutton_tonal
            STYLE.Widget_Material3_Button_TextButton_Icon -> R.layout.m3_iconbutton_standard
            STYLE.Widget_Material3_Button_OutlinedButton_Icon -> R.layout.m3_iconbutton_outlined

            else -> R.layout.mdc_iconbutton
        }
        LayoutInflater.from(context).inflate(layout, this)
        button = this.getChildAt(0) as MaterialButton
    }

    override fun addView(child: View?) = throw UnsupportedOperationException("")

    override fun setOnClickListener(l: OnClickListener?) = button.setOnClickListener(l)

    fun addOnCheckedChangeListener(l: OnCheckedChangeListener) =
        button.addOnCheckedChangeListener(l)

    fun clearOnCheckedChangeListeners() = run { button.clearOnCheckedChangeListeners() }

    override fun getBackgroundTintList() = button.backgroundTintList

    override fun getBackgroundTintMode() = button.backgroundTintMode

    fun getCornerRadius() = button.cornerRadius

    fun getIcon(): Drawable? = button.icon

    fun getIconPadding() = button.iconPadding

    fun getIconSize() = button.iconSize

    fun getIconTint(): ColorStateList? = button.iconTint

    fun getIconTintMode(): PorterDuff.Mode? = button.iconTintMode

    fun getRippleColor() = button.rippleColor

    fun getShapeAppearanceModel() = button.shapeAppearanceModel

    fun getStrokeColor(): ColorStateList? = button.strokeColor

    fun getStrokeWidth() = button.strokeWidth

    fun isCheckable() = button.isCheckable

    fun isChecked() = button.isChecked

    fun removeOnCheckedChangeListener(l: OnCheckedChangeListener) =
        button.removeOnCheckedChangeListener(l)

    override fun setBackground(b: Drawable) = run { button.background = b }

    override fun setBackgroundColor(color: Int) = button.setBackgroundColor(color)

    override fun setBackgroundResource(resId: Int) = button.setBackgroundResource(resId)

    override fun setBackgroundTintList(t: ColorStateList?) = run { button.backgroundTintList = t }

    override fun setBackgroundTintMode(m: PorterDuff.Mode?) = run { button.backgroundTintMode = m }

    fun setCheckable(c: Boolean) = run { button.isCheckable = c }

    fun setChecked(c: Boolean) = run { button.isChecked = c }

    fun setCornerRadius(r: Int) = run { button.cornerRadius = r }

    fun setCornerRadiusResource(i: Int) = button.setCornerRadiusResource(i)

    fun setIcon(i: Drawable) = run { button.icon = i }

    fun setIconPadding(p: Int) = run { button.iconPadding = p }

    fun setIconResource(i: Int) = button.setIconResource(i)

    fun setIconSize(@Px s: Int) = run { button.iconSize = s }

    fun setIconTint(t: ColorStateList) = run { button.iconTint = t }

    fun setIconTintMode(p: PorterDuff.Mode) = run { button.iconTintMode = p }

    fun setIconTintResource(i: Int) = button.setIconTintResource(i)

    override fun setPressed(b: Boolean) = run { button.isPressed = b }

    fun setRippleColor(c: ColorStateList) = run { button.rippleColor = c }

    fun setRippleColorResource(i: Int) = button.setRippleColorResource(i)

    fun setShapeAppearanceModel(m: ShapeAppearanceModel) = run { button.shapeAppearanceModel = m }

    fun setStrokeColor(c: ColorStateList) = run { button.strokeColor = c }

    fun setStrokeColorResource(i: Int) = button.setStrokeColorResource(i)

    fun setStrokeWidth(w: Int) = run { button.strokeWidth = w }

    fun setStrokeWidthResource(i: Int) = button.setStrokeWidthResource(i)

    fun toggle() = button.toggle()
}
