package com.ymkiux.compose.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * style info of the empty view
 * @param emptyImagePainter show image from resources
 * @param emptyDesc show text from below in image message
 */
data class EmptyDataInfo(val emptyImagePainter: Painter, val emptyDesc: String)

/**
 * style info of the divider
 * @param color color of the divider line
 * @param thickness thickness of the divider line
 */
data class DividerStyleInfo(
    val color: Color = Color.Gray,
    val thickness: Dp = 0.5.dp
)

/**
 * composable of the default empty view
 * @param emptyDataInfo style info of the empty view
 * @param isEmptyPlaceholder when empty dataSet ,whether show status of the empty view
 */
@Composable
fun EmptyDefault(emptyDataInfo: EmptyDataInfo, isEmptyPlaceholder: Boolean) {
    if (isEmptyPlaceholder) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(emptyDataInfo.emptyImagePainter, "")
                Text(text = emptyDataInfo.emptyDesc)
            }
        }
    }
}

/**
 * composable of the recycleView show
 * @param modifier the modifier to apply to this lazyColumn layout
 * @param allModifier the modifier to apply to this box layout
 * @param dataSet the dataSet to apply show to this composable
 * @param emptyDataInfo the emptyDataInfo layout of the dataSet empty show
 * @param columnNameItemContext the columnNameItemContext to apply to this recycleView item layout
 * @param columnItemContext the columnItemContext to apply to this recycleView context layout
 * @param dividerStyleInfo the dividerStyleInfo to apply to this divider style
 * @param isEmptyPlaceholder the isEmptyPlaceholder to apply to this empty dataSet whether to show
 * @param emptyViewCompose the emptyViewCompose to apply to this empty composable
 */
@Composable
fun <T> RecycleView(
    modifier: Modifier = Modifier,
    allModifier: Modifier = Modifier,
    dataSet: List<T>,
    emptyDataInfo: EmptyDataInfo,
    columnNameItemContext: @Composable() (LazyItemScope.() -> Unit),
    columnItemContext: @Composable() (LazyItemScope.(Int, T) -> Unit),
    dividerStyleInfo: DividerStyleInfo = DividerStyleInfo(),
    isEmptyPlaceholder: Boolean = true,
    emptyViewCompose: @Composable() (Boolean) -> Unit = { EmptyDefault(emptyDataInfo, it) },
) {
    when (dataSet.isEmpty()) {
        true -> {
            emptyViewCompose(isEmptyPlaceholder)
        }
        false -> {
            Box(modifier = allModifier) {
                LazyColumn(
                    modifier = modifier
                ) {
                    item {
                        columnNameItemContext()
                    }
                    itemsIndexed(dataSet) { index, x ->
                        Row {
                            columnItemContext(index, x)
                        }
                        if (index < dataSet.size - 1)
                            Divider(
                                color = dividerStyleInfo.color,
                                thickness = dividerStyleInfo.thickness
                            )
                    }
                }
            }
        }
    }
}