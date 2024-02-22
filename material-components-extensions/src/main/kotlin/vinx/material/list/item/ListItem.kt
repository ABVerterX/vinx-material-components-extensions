package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RestrictTo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.card.MaterialCardView
import vinx.material.databinding.ListItemBaseBinding
import vinx.material.internal.dp
import vinx.material.internal.firstBaselineToTopHeightCompat
import vinx.material.internal.lastBaselineToBottomHeightCompat
import vinx.material.internal.textWithVisibility
import vinx.material.list.control.Control

@Suppress("LeakingThis")
open class ListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    companion object {
        const val LINE_MODE_EXPANDABLE = 0
        const val LINE_MODE_ONE_LINE = 1
        const val LINE_MODE_TWO_LINE = 2
        const val LINE_MODE_THREE_LINE = 3
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    val layoutInflater = (context as Activity).layoutInflater

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    val binding = ListItemBaseBinding.inflate(layoutInflater, this, true)

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var controlView: FrameLayout = binding.frameControl

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    protected var controlBinding: ViewBinding? = null

    private val onListItemClickListeners = arrayListOf<View.OnClickListener>()

    var control: Control? = null
        set(value) {
            if (value != null) {
                controlBinding = value.attach(this)
            } else {
                binding.frameControl.removeAllViews()
            }
            field = value
        }

    var isControlSeparated: Boolean = false
        set(value) {
            binding.divider.visibility = if (value) {
                View.VISIBLE
            } else {
                View.GONE
            }
            field = value
        }

    var lineMode = LINE_MODE_TWO_LINE
        set(value) {
            field = value
            updateLayout()
        }

    var title: String? = null
        set(value) {
            binding.textListItemPrimary.textWithVisibility = value
            field = value
        }

    var summary: String? = null
        set(value) {
            binding.textListItemSecondary.textWithVisibility = value
            field = value
        }

    var meta: String? = null
        set(value) {
            binding.textListItemMeta.textWithVisibility = value
            field = value
        }

    init {
        setOnClickListener { view ->
            if (!isControlSeparated) {
                control?.onClick()
            }
            onListItemClickListeners.forEach {
                it.onClick(view)
            }
        }
        isControlSeparated = false
        cardElevation = 0f
        radius = 0f
        isClickable = true
        updateLayout()
    }

    fun addOnListItemClickListener(listener: OnClickListener) {
        onListItemClickListeners.add(listener)
    }

    private fun updateLayout() {
        (binding.root.layoutParams as ViewGroup.LayoutParams).height = when (lineMode) {
            LINE_MODE_EXPANDABLE -> 48.dp
            LINE_MODE_ONE_LINE -> 56.dp
            LINE_MODE_TWO_LINE -> 72.dp
            LINE_MODE_THREE_LINE -> 88.dp
            else -> throw IllegalArgumentException()
        }
        (binding.textListItemPrimary.layoutParams as ConstraintLayout.LayoutParams).setMargins(
            0, 32.dp - binding.textListItemPrimary.firstBaselineToTopHeightCompat, 0, 0
        )
        (binding.textListItemMeta.layoutParams as ConstraintLayout.LayoutParams).setMargins(
            0, 32.dp - binding.textListItemMeta.firstBaselineToTopHeightCompat, 0, 0
        )
        (binding.textListItemSecondary.layoutParams as ConstraintLayout.LayoutParams).setMargins(
            0,
            20.dp - binding.textListItemSecondary.firstBaselineToTopHeightCompat - binding.textListItemPrimary.lastBaselineToBottomHeightCompat,
            0,
            0
        )
    }
}