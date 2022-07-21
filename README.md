[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Latest release](https://img.shields.io/github/v/release/JetBrains/compose-jb?color=brightgreen&label=latest%20release)](https://github.com/JetBrains/compose-jb/releases/latest)

## compose-reference

```css
作为一个compose常用组件封装示例
```

### 简单使用

#### dropdownMenu

```java
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