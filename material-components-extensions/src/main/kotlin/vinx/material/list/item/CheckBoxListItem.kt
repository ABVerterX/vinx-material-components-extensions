package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import vinx.material.databinding.ItemActionCheckBoxBinding
import vinx.material.databinding.ItemActionRadioButtonBinding

class CheckBoxListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ListItem(context, attrs, defStyleAttr) {

    init {
        val actionBinding = ItemActionCheckBoxBinding.inflate(
            (context as Activity).layoutInflater,
            binding.frameTrailing,
            true
        )
        this.setOnClickListener {
            actionBinding.check.toggle()
        }
    }

}