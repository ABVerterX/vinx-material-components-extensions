package vinx.material.list.control

import androidx.viewbinding.ViewBinding
import vinx.material.databinding.ControlCheckBoxBinding
import vinx.material.list.item.ListItem

class CheckBoxControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlCheckBoxBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        (binding as ControlCheckBoxBinding).check.isClickable = listItem.isControlSeparated
        return binding as ControlCheckBoxBinding
    }

    override fun onClick() {
        (binding as ControlCheckBoxBinding).check.toggle()
    }
}