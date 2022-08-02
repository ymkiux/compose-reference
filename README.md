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
val selectIndex by remember{mutableStateOf(mutableListOf(0))}
dropdownMenu(selectListInfo,selectIndex){v1,v2->
  selectIndex[0]=v2
}
```

##### 注释

```xml
DataViewModel类uiState为StateFlow状态流 使用之后后者数据集显示随之改变
```

#### radioButton

##### 仅有一个

```
仅需tags initialState radioState参数
```

##### 多个选一个

```
仅需tags selectIndex参数
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

- 其他相关可看注释