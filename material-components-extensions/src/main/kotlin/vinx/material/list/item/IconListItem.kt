package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import vinx.material.databinding.ItemActionCheckBoxBinding
import vinx.material.databinding.ItemActionIconBinding

class IconListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ListItem(context, attrs, defStyleAttr) {

    private val actionBinding = ItemActionIconBinding.inflate(
        (context as Activity).layoutInflater,
        binding.frameTrailing,
        true
    )

    fun setImageBitmap(bitmap: Bitmap) {
        actionBinding.image.setImageBitmap(bitmap)
    }

}