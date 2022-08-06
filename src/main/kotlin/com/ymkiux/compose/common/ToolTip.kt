package com.ymkiux.compose.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * data class form the tooltip element
 * @param toolTipDesc show text from tooltip
 * @param tooltipModifier the modifier to be applied to the tooltip layout
 * @param tooltipColor tooltip background color
 * @param tooltipShape defines the surface's shape as well its shadow. a shadow is only displayed if the elevation is greater than zero
 * @param toolTipDescModifier the modifier to be applied to the tooltip text layout
 * @param toolTipDescColor tooltip title color
 * @param tooltipModuleModifier composable content of the tooltip
 * @param tooltipPlacement defines position of the tooltip
 * @param tooltipShowDelayMillis delay in milliseconds to show
 */
data class ToolTipInfo @OptIn(ExperimentalFoundationApi::class) constructor(
    val toolTipDesc: String,
    val tooltipModifier: Modifier = Modifier.shadow(4.dp),
    val tooltipColor: Color = Color(117, 117, 117),
    val tooltipShape: RoundedCornerShape = RoundedCornerShape(4.dp),
    val toolTipDescModifier: Modifier = Modifier.padding(10.dp),
    val toolTipDescColor: Color = Color.White,
    val tooltipModuleModifier: Modifier = Modifier,
    val tooltipPlacement: TooltipPlacement = TooltipPlacement.CursorPoint(
        alignment = Alignment.BottomEnd,
        offset = DpOffset((-16).dp, 0.dp)
    ),
    val tooltipShowDelayMillis: Int = 0
)

/**
 * composable content of the content to tooltip show
 * @param toolTipInfo reference in  ToolTipInfo
 * @param content wrapped control in composable context
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tooltip(toolTipInfo: ToolTipInfo, content: @Composable ColumnScope.() -> Unit) {
    Column {
        TooltipArea(
            tooltip = {
                Surface(
                    modifier = toolTipInfo.tooltipModifier,
                    color = toolTipInfo.tooltipColor,
                    shape = toolTipInfo.tooltipShape
                ) {
                    Text(
                        text = toolTipInfo.toolTipDesc,
                        modifier = toolTipInfo.toolTipDescModifier,
                        color = toolTipInfo.toolTipDescColor
                    )
                }
            },
            modifier = toolTipInfo.tooltipModuleModifier,
            delayMillis = toolTipInfo.tooltipShowDelayMillis,
            tooltipPlacement = toolTipInfo.tooltipPlacement
        ) {
            content()
        }
    }
}