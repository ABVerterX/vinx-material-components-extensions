package vinx.material.list.control

import android.graphics.Bitmap
import androidx.viewbinding.ViewBinding
import com.google.android.material.shape.ShapeAppearanceModel
import vinx.material.databinding.ControlAvatarBinding
import vinx.material.internal.dp
import vinx.material.list.item.ListItem

class AvatarControl : Control() {

    private var imageBitmap: Bitmap? = null

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlAvatarBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        (binding as ControlAvatarBinding).image.apply {
            isClickable = listItem.isControlSeparated
            shapeAppearanceModel =
                ShapeAppearanceModel.builder().setAllCornerSizes(20f.dp).build()
            imageBitmap?.let { setImageBitmap(imageBitmap) }
        }
        return binding as ControlAvatarBinding
    }

    override fun onClick() {

    }

    fun setImageBitmap(bitmap: Bitmap) {
        imageBitmap = bitmap
        binding?.let { (binding as ControlAvatarBinding).image.setImageBitmap(bitmap) }
    }

    fun setImageShapeAppearanceModel(model: ShapeAppearanceModel) {
        (binding as ControlAvatarBinding).image.shapeAppearanceModel = model
    }
}