package vinx.material.list.control

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.slider.Slider
import vinx.material.databinding.ControlCheckBoxBinding
import vinx.material.internal.dp
import vinx.material.list.item.ListItem

class SliderControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding? {
        listItem.addView(
            Slider(listItem.context),
            ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(4.dp, 44.dp, 4.dp, 0.dp)
            }
        )
        return null
    }

    override fun onClick() {

    }
}