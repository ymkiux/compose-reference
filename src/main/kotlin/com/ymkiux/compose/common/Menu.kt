package com.ymkiux.compose.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

/**
 *  The utilization characteristics change with the changes of the data introduced from the outside world
 */
@Composable
fun dropdownMenu(
    list: List<String>,
    selectIndex: MutableList<Int>,
    modifier: Modifier = Modifier,
    context: (String, Int) -> Unit
) {
    if (list.isEmpty()) throw IllegalArgumentException("dropdownMenu list value was null.")
    var mExpanded by remember { mutableStateOf(false) }
    val mSelectedText = derivedStateOf {
        list.map { it.uppercase() }
    }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    Column(modifier.then(Modifier), verticalArrangement = Arrangement.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                mSelectedText.value[selectIndex.iterator().next()], modifier = Modifier.clickable {
                    mExpanded = true
                }.onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                }
            )
            Icon(Icons.Default.ArrowDropDown, "", modifier = Modifier.clickable {
                mExpanded = true
            })
        }
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            list.forEachIndexed { index, label ->
                DropdownMenuItem(onClick = {
                    selectIndex[0] = index
                    context(label, index)
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}