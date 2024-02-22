package vinx.material.list.control

import androidx.viewbinding.ViewBinding
import vinx.material.databinding.ControlCheckBoxBinding
import vinx.material.databinding.ControlSwitchBinding
import vinx.material.list.item.ListItem

class SwitchControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlSwitchBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        (binding as ControlSwitchBinding).switches.isClickable = listItem.isControlSeparated
        return binding as ControlSwitchBinding
    }

    override fun onClick() {
        (binding as ControlSwitchBinding).switches.toggle()
    }
}