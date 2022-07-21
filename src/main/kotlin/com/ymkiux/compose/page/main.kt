package com.ymkiux.compose.page

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

/**
 * As a reference to the launch window
 */
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            size = WindowSize(300.dp, 300.dp),
            position = WindowPosition.Aligned(Alignment.Center)
        ),
        resizable = false,
        undecorated = true,
        transparent = true
    ) {
        WindowDraggableArea {

        }
    }
}


