package vinx.material.list.control

import androidx.viewbinding.ViewBinding
import vinx.material.databinding.ControlRadioButtonBinding
import vinx.material.list.item.ListItem

class RadioButtonControl : Control() {

    override fun attach(listItem: ListItem): ViewBinding {
        binding = ControlRadioButtonBinding.inflate(
            listItem.layoutInflater,
            listItem.controlView,
            true
        )
        (binding as ControlRadioButtonBinding).radio.isClickable = listItem.isControlSeparated
        return binding as ControlRadioButtonBinding
    }

    override fun onClick() {
        (binding as ControlRadioButtonBinding).radio.isChecked = true
    }

}