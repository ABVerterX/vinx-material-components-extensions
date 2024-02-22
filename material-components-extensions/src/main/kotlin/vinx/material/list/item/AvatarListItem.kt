package vinx.material.list.item

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import com.google.android.material.shape.ShapeAppearanceModel
import vinx.material.databinding.ItemActionAvatarBinding
import vinx.material.internal.dp

class AvatarListItem @JvmOverloads constructor(
    private val context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ListItem(context, attrs, defStyleAttr) {

    private val actionBinding = ItemActionAvatarBinding.inflate(
        (context as Activity).layoutInflater,
        binding.frameTrailing,
        true
    )

    init {
        actionBinding.image.shapeAppearanceModel =
            ShapeAppearanceModel.builder().setAllCornerSizes(20f.dp).build()
    }

    fun setImageBitmap(bitmap: Bitmap) {
        actionBinding.image.setImageBitmap(bitmap)
    }

    fun setImageShapeAppearanceModel(model: ShapeAppearanceModel) {
        actionBinding.image.shapeAppearanceModel = model
    }

}