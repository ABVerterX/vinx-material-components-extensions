package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import com.google.android.material.shape.ShapeAppearanceModel
import vinx.material.databinding.ItemActionAvatarBinding
import vinx.material.databinding.ItemActionThumbnailBinding
import vinx.material.internal.dp

class ThumbnailListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ListItem(context, attrs, defStyleAttr) {

    private val actionBinding = ItemActionThumbnailBinding.inflate(
        (context as Activity).layoutInflater,
        binding.frameTrailing,
        true
    )

    init {

    }

    fun setImageBitmap(bitmap: Bitmap) {
        actionBinding.image.setImageBitmap(bitmap)
    }


}