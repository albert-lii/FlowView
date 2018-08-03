# FlowView

![releasesvg] ![apisvg] [![license][licensesvg]][license] 

## 关于
FlowView是一款流布局控件，采用Adapter模式，可以自定义item，更加灵活，使用起来就和ListView与BaseAdapter搭配一样简单，提供各种对齐方式，可以自适应高度，也可以设置item的高度，可以控制item的最大显示行数...

## 演示
先来看看效果吧！  

![演示][demogif]

## 添加依赖
- 使用Gradle
```java
  // 注：如果添加依赖成功，则此句不必添加，此句作用仅为当项目在被审核时，紧急需要使用时添加
   allprojects {
       repositories {
           ...
           // 如果添加依赖时，报找不到项目时（项目正在审核），可以添加此句maven地址，如果找到项目，可不必添加
           maven { url "https://dl.bintray.com/albertlii/android-maven/" }
       }
    }
    
    dependencies {
         compile 'com.liyi.view:flowview:1.2.1'
    }
```
- 使用Maven
```java
 <dependency>
   <groupId>com.liyi.view</groupId>
   <artifactId>flowview</artifactId>
   <version>1.2.1</version>
   <type>pom</type>
 </dependency>
```

## 自定义属性
- flow_horizontalAlign（item 在一行中的横向对齐方式，默认为 left） 
  - left（item 左对齐）
  - middle（item 横向居中对齐）
  - right（item 右对齐）  
  
- flow_verticalAlign（item 在一行中的纵向对齐方式，默认为 middle，当设置 flow_height 时，此属性无效）   
  - top（item 上对齐）
  - middle（item 纵向居中对齐）
  - bottom（item 底部对齐）  

- flow_height（流布局的 item 的高度，默认为自适应）
- flow_horizontalSpace（流布局的 item 横向间距，默认为 10px）
- flow_verticalSpace（流布局的 item 纵向间距，默认为 10px）
- flow_maxRow（流布局最多显示的行数，默认为不限制）

## 使用方法
### XML 中 添加 FlowView
```java
 <com.liyi.flow.FlowView
    android:id="@+id/flowVi"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

### 代码中使用 FlowView
#### 三种适配器   
- [`SimpleFlowAdapter`][SimpleFlowAdapter]：简用适配器，直接使用，封装有默认布局，[demo示例][SimpleFlowActivity]   
- [`QuickFlowAdapter`][QuickFlowAdapter]：快捷适配器，继承自`BaseFlowAdapter`,[demo示例][QuickFlowActivity]   
- [`BaseFlowAdapter`][BaseFlowAdapter]：基础适配器，不推荐直接使用，推荐使用`QuickFlowAdapter`

#### 简单示例  
```java
注：此处使用的是 SimpleFlowAdapter，SimpleFlowAdapter 必须设置 setLoadData() 来处理数据
 mAdapter = new SimpleFlowAdapter(mList);
 mAdapter.setLoadData(new SimpleFlowAdapter.LoadData<String>() {
      @Override
      public void onLoadData(int position, String item, TextView textView) {
           textView.setText(item);
      }
});
flowView.setAdapter(mAdapter);
// 单击事件
flowView.setOnItemClickListener(new FlowView.OnItemClickListener() {
    @Override
    public void onItemClick(int position, View view) {
        Toast.makeText(SimpleFlowActivity.this, "我是" + position + "号", Toast.LENGTH_SHORT).show();
    }
});
```

## 赞赏
如果你感觉 `FlowView` 帮助到了你，可以点右上角 "Star" 支持一下 谢谢！:blush:

## LICENSE
Copyright 2017 liyi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[releasesvg]: https://img.shields.io/badge/version-1.2.1-brightgreen.svg
[apisvg]: https://img.shields.io/badge/sdk-9+-brightgreen.svg
[licensesvg]: https://img.shields.io/badge/license-Apache--2.0-blue.svg
[license]:http://www.apache.org/licenses/LICENSE-2.0
[statussvg]:https://img.shields.io/librariesio/github/phoenixframework/phoenix.svg

[SimpleFlowAdapter]:https://github.com/albert-lii/FlowView/blob/master/flowview/src/main/java/com/liyi/flow/adapter/SimpleFlowAdapter.java
[QuickFlowAdapter]:https://github.com/albert-lii/FlowView/blob/master/flowview/src/main/java/com/liyi/flow/adapter/QuickFlowAdapter.java
[BaseFlowAdapter]:https://github.com/albert-lii/FlowView/blob/master/flowview/src/main/java/com/liyi/flow/adapter/BaseFlowAdapter.java

[SimpleFlowActivity]:https://github.com/albert-lii/FlowView/blob/master/app/src/main/java/com/liyi/example/SimpleFlowActivity.java
[QuickFlowActivity]:https://github.com/albert-lii/FlowView/blob/master/app/src/main/java/com/liyi/example/QuickFlowActivity.java

[SUtils]:https://github.com/albert-lii/SUtils
[AutoGridView]:https://github.com/albert-lii/AutoGridView
[demogif]:https://github.com/albert-lii/FlowView/blob/master/screenshot/demo.gif

