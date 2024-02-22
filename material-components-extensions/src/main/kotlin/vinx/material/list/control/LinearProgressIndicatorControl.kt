package vinx.material.list.control

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.slider.Slider
import vinx.material.databinding.ControlCheckBoxBinding
import vinx.material.internal.dp
import vinx.material.list.item.ListItem

class LinearProgressIndicatorControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding? {
        (listItem.binding.root.layoutParams as ViewGroup.LayoutParams).height =
            (listItem.binding.root.layoutParams as ViewGroup.LayoutParams).height + 12.dp
        listItem.addView(
            LinearProgressIndicator(listItem.context).apply { progress = 40 },
            ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16.dp, 68.dp, 16.dp, 0.dp)
            }
        )
        return null
    }

    override fun onClick() {

    }
}