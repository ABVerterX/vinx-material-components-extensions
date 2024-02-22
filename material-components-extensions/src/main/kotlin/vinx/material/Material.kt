package vinx.material

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.annotation.StyleRes
import com.google.android.material.R
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.DynamicColorsOptions
import com.google.android.material.color.MaterialColors
import com.google.android.material.color.utilities.DynamicColor

class Material {
    companion object {
        private const val INVALID_COLOR = Int.MIN_VALUE

        @JvmStatic
        fun isMaterialThemeEnabled(context: Context) =
            getAttrColor(context, R.attr.colorSurface) != INVALID_COLOR

        @JvmStatic
        fun isMaterial3Enabled(context: Context) =
            getAttrColor(context, R.attr.colorSurfaceContainer) != INVALID_COLOR

        @JvmStatic
        fun isMaterial2Enabled(context: Context) =
            isMaterialThemeEnabled(context) && !isMaterial3Enabled(context)

        @JvmStatic
        fun isMaterialYouEnabled(context: Context) = false

        @ColorInt
        @JvmStatic
        private fun getAttrColor(context: Context, @AttrRes attrResId: Int) =
            MaterialColors.getColor(context, attrResId, INVALID_COLOR)

    }
}