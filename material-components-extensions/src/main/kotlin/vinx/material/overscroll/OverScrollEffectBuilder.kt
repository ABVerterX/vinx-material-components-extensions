package vinx.material.overscroll

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.AbsListView
import android.widget.EdgeEffect
import android.widget.HorizontalScrollView
import widget.EdgeEffect2
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import me.weishu.reflection.Reflection
import java.util.Objects

class OverScrollEffectBuilder(private val view: View) {
    private var styleType = 2

    fun useMaterial2Style() {
        styleType = 2
    }

    fun useMaterial1Style() {
        styleType = 1
    }

    fun useMaterial3Style() {
        styleType = 3
    }

    @SuppressLint("PrivateApi")
    fun build() {
        Reflection.unseal(view.context)
        if (true) {
            when (view) {
                is ScrollView -> run {
                    stylizeEdgeEffect(
                        ScrollView::class.java.getDeclaredField("mEdgeGlowTop")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        ScrollView::class.java.getDeclaredField("mEdgeGlowBottom")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                }

                is NestedScrollView -> run {
                    stylizeEdgeEffect(
                        NestedScrollView::class.java.getDeclaredField("mEdgeGlowTop")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        NestedScrollView::class.java.getDeclaredField("mEdgeGlowBottom")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                }

                is AbsListView -> run {
                    stylizeEdgeEffect(
                        AbsListView::class.java.getDeclaredField("mEdgeGlowTop")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        AbsListView::class.java.getDeclaredField("mEdgeGlowBottom")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                }

                is HorizontalScrollView -> run {
                    stylizeEdgeEffect(
                        HorizontalScrollView::class.java.getDeclaredField("mEdgeGlowLeft")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        HorizontalScrollView::class.java.getDeclaredField("mEdgeGlowRight")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                }

                is RecyclerView -> run {
                    stylizeEdgeEffect(
                        RecyclerView::class.java.getDeclaredField("mTopGlow")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        RecyclerView::class.java.getDeclaredField("mBottomGlow")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        RecyclerView::class.java.getDeclaredField("mLeftGlow")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                    stylizeEdgeEffect(
                        RecyclerView::class.java.getDeclaredField("mRightGlow")
                            .apply { isAccessible = true }.get(view), if (styleType == 3) 1 else 0
                    )
                }
            }
        }
    }

    private fun stylizeEdgeEffect(effect: Any?, effectType: Int) {
        (effect as EdgeEffect).let {
            it.color = MaterialColors.getColor(
                view, when (styleType) {
                    2 -> com.google.android.material.R.attr.colorSecondary
                    1 -> android.R.attr.colorControlHighlight
                    else -> android.R.attr.colorControlHighlight
                }
            )
            it::class.java.getDeclaredField("mEdgeEffectType").apply { isAccessible = true }
                .set(effect, effectType)
        }
    }
}