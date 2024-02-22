package vinx.material.list.control

import androidx.viewbinding.ViewBinding
import vinx.material.R
import vinx.material.databinding.ControlCheckBoxBinding
import vinx.material.databinding.ControlIconButtonBinding
import vinx.material.list.item.ListItem

class IconButtonControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlIconButtonBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        listItem.isControlSeparated = true
        (binding as ControlIconButtonBinding).button.isClickable = listItem.isControlSeparated
        (binding as ControlIconButtonBinding).button.setIconResource(R.drawable.outline_open_in_new_24)
        return binding as ControlIconButtonBinding
    }

    override fun onClick() {

    }
}