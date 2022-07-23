package com.ymkiux.compose.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A collection of radio buttons that allow the user to select or deselect an option from a group or a single option
 * @param tags data set
 * @param initialState init select state
 * @param radioButtonColors used to resolve the color used for this in different states
 * @param mainModifier the modifier to be applied to the Row outermost
 * @param childModifier the modifier to be applied to the Row sublevel
 * @param radioState used for this set select is only one option. tags is arrayListOf("")
 * @param selectIndex callback used for select set index
 */
@Composable
fun radioButton(
    tags: List<String>,
    initialState: MutableList<Boolean> = mutableListOf(false),
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)),
    mainModifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    radioState: (Boolean) -> Unit = {},
    selectIndex: (Int) -> Unit = {}
) {
    val selectedTag = remember { mutableStateOf(tags[0]) }
    val radio = derivedStateOf { initialState }
    Row(modifier = mainModifier) {
        tags.forEach {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = childModifier) {
                RadioButton(
                    selected = if (tags.size != 1) it == selectedTag.value else radio.value[0],
                    onClick = {
                        if (tags.size == 1) {
                            radio.value[0] = !radio.value[0]
                            radioState.invoke(radio.value[0])
                        } else {
                            selectedTag.value = it
                            selectIndex.invoke(tags.indexOf(it))
                        }
                    },
                    colors = radioButtonColors
                )
                Text(text = it)
            }
        }
    }
}