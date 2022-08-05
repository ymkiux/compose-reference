package com.ymkiux.compose.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * data class form the main purpose of creation is to update the data status in real time
 */
data class UiState(val desc: String = "", var selectState: Boolean = false, val time: Long = System.currentTimeMillis())

/**
 * A collection of radio buttons that allow the user to select or deselect an option from a group or a single option
 * @param tags data set
 * @param radioButtonColors used to resolve the color used for this in different states
 * @param mainModifier the modifier to be applied to the Row outermost
 * @param childModifier the modifier to be applied to the Row sublevel
 * @param oneSetSelectState used for this set select is only one option. tags is arrayListOf("")
 * @param moreSetSelectState callback used for select set index. when used for single selection, you need to customize the viewModel logic
 */
@Composable
fun radioButton(
    tags: List<UiState> = mutableListOf(),
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)),
    mainModifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    oneSetSelectState: (Boolean) -> Unit = {},
    moreSetSelectState: (Int, Boolean) -> Unit = { _, _ -> }
) {
    when (tags.isEmpty()) {
        true -> throw KotlinNullPointerException("tags can not empty list")
        false -> {
            Row(modifier = mainModifier) {
                tags.forEachIndexed { index, it ->
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = childModifier) {
                        RadioButton(
                            selected = tags[index].selectState,
                            onClick = {
                                if (tags.size == 1) {
                                    tags[index].selectState = !tags[index].selectState
                                    oneSetSelectState.invoke(tags[index].selectState)
                                } else {
                                    tags[index].selectState = !it.selectState
                                    moreSetSelectState.invoke(index, tags[index].selectState)
                                }
                            },
                            colors = radioButtonColors
                        )
                        Text(text = it.desc)
                    }
                }
            }
        }
    }
}