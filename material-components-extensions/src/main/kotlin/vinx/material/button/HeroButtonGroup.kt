package vinx.material.button

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.card.MaterialCardView

class HeroButtonGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr) {

    companion object {
        const val LINEAR_LAYOUT_TAG = "LinearLayoutCompat"
    }

    private var linearLayout: LinearLayoutCompat

    init {
        elevation = 0F
        radius = 0f
        setCardBackgroundColor(Color.TRANSPARENT)

        linearLayout = LinearLayoutCompat(context)
        linearLayout.tag = LINEAR_LAYOUT_TAG
        linearLayout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
        )
        addView(linearLayout)
    }

    override fun addView(child: View?) {
        if (child?.tag == LINEAR_LAYOUT_TAG) {
            super.addView(child)
        } else {
            if (child is HeroButton) {
                child.layoutParams =
                    LinearLayoutCompat.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    ).apply { weight = 1F }
            }
            linearLayout.addView(child)
        }
    }
}