package vinx.material.list.control

import androidx.viewbinding.ViewBinding
import vinx.material.list.item.ListItem

abstract class Control {

    var binding: ViewBinding? = null

    abstract fun attach(listItem: ListItem): ViewBinding?
    abstract fun onClick()
}