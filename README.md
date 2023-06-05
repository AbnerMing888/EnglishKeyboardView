# EnglishKeyboardView
一个超级简单的自定义英文和数字键盘，主要用于车牌输入等功能，多种属性配置，可实现多种效果。

## 效果展示

<img src="image/board.png" width="360px" />

## 快速开始

1、在你的根项目下的build.gradle文件下，引入maven。

```groovy
allprojects {
    repositories {
        maven { url "https://gitee.com/AbnerAndroid/almighty/raw/master" }
    }
}
```
2、在你需要使用的Module中build.gradle文件下，引入依赖。

```groovy
dependencies {
    implementation 'com.vip:board:1.0.0'
}
```
3、XML引入即可

```xml
   <com.vip.board.EnglishKeyboardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

```

4、属性介绍

| 属性                            | 类型        | 概述                            |
| ----------------------------- | --------- | ----------------------------- |
| ek\_background                | color     | 整体的背景颜色                       |
| ek\_rect\_spacing             | dimension | 格子的边距                         |
| ek\_rect\_height              | dimension | 格子的高度                         |
| ek\_rect\_margin\_top         | dimension | 格子的距离上边                       |
| ek\_margin\_left\_right       | dimension | 左右距离                          |
| ek\_margin\_top               | dimension | 上边距离                          |
| ek\_margin\_bottom            | dimension | 下边距离                          |
| ek\_rect\_background          | reference | 格子的背景                         |
| ek\_rect\_select\_background  | reference | 格子选择后的背景                      |
| ek\_rect\_text\_size          | dimension | 格子的文字大小                       |
| ek\_rect\_text\_color         | color     | 格子的文字颜色                       |
| ek\_rect\_select\_text\_color | color     | 格子的文字选中颜色                     |
| ek\_is\_show\_complete        | boolean   | 是否显示完成按钮                      |
| ek\_complete\_text\_size      | dimension | 完成按钮文字大小                      |
| ek\_complete\_text\_color     | color     | 完成按钮文字颜色                      |
| ek\_complete\_text            | string    | 完成按钮文字内容                      |
| ek\_complete\_margin\_top     | dimension | 完成按钮距离上边                      |
| ek\_complete\_margin\_bottom  | dimension | 完成按钮距离下边                      |
| ek\_complete\_margin\_right   | dimension | 完成按钮距离右边                      |
| ek\_other\_lines\_margin      | dimension | 其他行边距                         |
| ek\_is\_num\_prohibit         | boolean   | 数字是否禁止                        |
| ek\_text\_prohibit\_color     | color     | 数字禁止颜色                        |
| ek\_text\_click\_effect       | boolean   | 是否触发点击效果，true点击后背景消失，false不消失 |

5、方法介绍

| 方法 | 参数 | 概述                 |
|  ----  |----|--------------------|
| keyboardContent | 回调函数 | 获取点击的省份简称简称信息      |
| keyboardDelete | 函数 | 删除省份简称简称信息         |
| keyboardComplete | 回调函数 | 键盘点击完成             |
| openProhibit | 函数 | 打开禁止（使领学港澳），使其可以点击 |


## 文章介绍

[Android自定义一个车牌字母选择键盘](https://juejin.cn/spost/7241114001323638839)

## 欢迎关注作者

微信搜索【Android干货铺】，或扫描下面二维码关注，查阅更多技术文章！

<img src="image/abner.jpg" width="200px" />

## License

```
Copyright (C) AbnerMing, EnglishKeyboardView Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```