[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Latest release](https://img.shields.io/github/v/release/JetBrains/compose-jb?color=brightgreen&label=latest%20release)](https://github.com/JetBrains/compose-jb/releases/latest)

## compose-reference

```css
作为一个compose常用组件封装示例
```

### 简单使用

#### dropdownMenu

```kotlin
val selectListInfo by DataViewModel.uiState.collectAsState()
val selectIndex by remember { mutableStateOf(mutableListOf(0)) }
dropdownMenu(selectListInfo, selectIndex) { _, s ->
    selectIndex[0] = s
}
```

##### 注释

```xml
DataViewModel类uiState为StateFlow状态流 使用之后后者数据集显示随之改变
```

#### radioButton

##### 仅有一个

```kotlin
var oneSelectState by remember { mutableStateOf(false) }
radioButton(
    listOf<UiState>(
        UiState(
            "单数据测试",
            oneSelectState
        )
    ), oneSetSelectState = {
        oneSelectState = it
    })
```

##### 多组选位

```kotlin
val moreSelectState by RadioInfoViewModel.uiState.collectAsState()
radioButton(
    moreSelectState
) { index, state ->
    //实现单选或者多选逻辑皆可自定义
    RadioInfo.update(index, state)
}
```

#### Notification

##### 初始化

```kotlin
fun main() = application {
    initNotification(
        appName,
        painterResource(wxData[0].selectedPath)
    )
}
```

##### 通知事件

```kotlin
NotificationKt.sendNotification("消息内容")
```

#### ToolTip
```kotlin
tooltip(ToolTipInfo("本地测试")) {
   Text("DEBUG", color = Color.White)
}
```
#### RecycleView
```text
作为垂直滚动列表的可组合控件 详情使用看注释即可
```