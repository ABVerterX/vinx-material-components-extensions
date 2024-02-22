package vinx.material.search

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.os.BuildCompat
import com.google.android.material.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import vinx.material.Material


typealias OriginSearchBar = com.google.android.material.search.SearchBar

class SearchBar @JvmOverloads constructor(
    private val baseContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialSearchBarStyle
) : OriginSearchBar(
    ContextThemeWrapper(baseContext, R.style.Theme_Material3_DayNight), attrs, defStyleAttr
) {
    companion object {
        val ORIGIN_CLASS = OriginSearchBar::class.java
    }

    init {
        if (Material.isMaterial2Enabled(baseContext)) {
            ORIGIN_CLASS.getDeclaredField("backgroundShape").run {
                isAccessible = true
                get(this@SearchBar) as MaterialShapeDrawable
            }.run {
                fillColor = ColorStateList.valueOf(getAttrColor(R.attr.colorSurface))
                elevation = dp2px(3F)
                shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
                    .setAllCornerSizes(AbsoluteCornerSize(dp2px(4F))).build()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                outlineAmbientShadowColor = 0xff000000.toInt()
                outlineSpotShadowColor = 0xff000000.toInt()
            }
        }
    }

    @ColorInt
    private fun getAttrColor(@AttrRes attrResId: Int): Int {
        return MaterialColors.getColor(baseContext, attrResId, Color.WHITE)
    }

    private fun dp2px(dp: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            widthMeasureSpec, MeasureSpec.makeMeasureSpec(dp2px(48F).toInt(), MeasureSpec.EXACTLY)
        )
    }
}