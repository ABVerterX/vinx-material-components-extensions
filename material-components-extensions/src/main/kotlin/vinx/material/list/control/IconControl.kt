package vinx.material.list.control

import android.graphics.Bitmap
import androidx.viewbinding.ViewBinding
import vinx.material.databinding.ControlIconBinding
import vinx.material.list.item.ListItem

class IconControl : Control() {

    private var imageBitmap: Bitmap? = null

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlIconBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        (binding as ControlIconBinding).image.apply {
            isClickable = listItem.isControlSeparated
            imageBitmap?.let { setImageBitmap(imageBitmap) }
        }
        return binding as ControlIconBinding
    }

    override fun onClick() {

    }

    fun setImageBitmap(bitmap: Bitmap) {
        imageBitmap = bitmap
        binding?.let { (binding as ControlIconBinding).image.setImageBitmap(bitmap) }
    }
}