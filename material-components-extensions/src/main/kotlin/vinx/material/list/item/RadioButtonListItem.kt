package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import vinx.material.databinding.ItemActionRadioButtonBinding

class RadioButtonListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ListItem(context, attrs, defStyleAttr) {

    init {
        val actionBinding = ItemActionRadioButtonBinding.inflate(
            (context as Activity).layoutInflater,
            binding.frameTrailing,
            true
        )
        this.setOnClickListener {
            actionBinding.radio.isChecked = true
        }
    }

}