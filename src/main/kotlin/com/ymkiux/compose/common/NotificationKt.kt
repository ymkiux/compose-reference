package com.ymkiux.compose.common
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


object NotificationKt {
    data class UiState(var message: String, var title: String, var painter: Painter? = null, var state: Boolean)

    private val uiState = MutableStateFlow(UiState(message = "", title = "", state = false))

    private val uiStateFlow = uiState.asStateFlow()

    /**
     * send notification message
     * @param message notification message
     */
    fun sendNotification(message: String) {
        uiState.value = uiState.value.copy(message = message, state = true)
    }

    /**
     *  Initialize the notification component
     *  @param title notification title
     *  @param painter notification icon. use painterResource to resources
     *  @param menu menu of the tray that will be shown to the user on the mouse click
     */
    @Composable
    fun ApplicationScope.initNotification(
        title: String,
        painter: Painter,
        menu: @Composable MenuScope.() -> Unit = {}
    ) {
        val collectAsState = uiStateFlow.collectAsState().value
        collectAsState.apply {
            val notification = Notification(title, message)
            val trayState = rememberTrayState()
            Tray(
                state = trayState,
                icon = painter,
                menu = menu
            )
            if (state) {
                trayState.sendNotification(notification)
                uiState.value = uiState.value.copy(state = false)
            }
            uiState.value = uiState.value.copy(title = title, painter = painter)
        }
    }
}