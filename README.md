# FlowView

## 关于
FlowView是一款流布局控件，采用Adapter模式，可以自定义item，更加灵活，使用起来就和ListView与BaseAdapter搭配一样简单，提供各种对齐方式，可以自适应
高度，也可以设置item的高度，可以控制item的最大显示行数...

## 推荐
- [SUtils][SUtils] 轻量的常用的工具类库
- [AutoGridView][AutoGridView] 宫格控件，QQ空间九宫格、普通宫格模式、点击添加照片...

## 演示
先来看看效果吧！  

![演示][demogif]

> **博客详情：http://blog.csdn.net/liyi1009365545/article/details/77963829**

## 添加依赖
```java
    dependencies {
         compile 'com.liyi:FlowView:1.0.6'
    }
```

## 自定义属性
- flow_horalign（item 在一行中的横向对齐方式）  
  - left（item 左对齐）
  - middle（item 横向居中对齐）
  - right（item 右对齐）  
  
- flow_vertalign（item 在一行中的纵向对齐方式，当设置 flow_height 时，此属性无效）  
  - top（item 上对齐）
  - middle（item 纵向居中对齐）
  - bottom（item 底部对齐）  

- flow_height（流布局的 item 的高度）
- flow_hspace（流布局的 item 横向间距）
- flow_vspace（流布局的 item 纵向间距）
- flow_maxrows（流布局最多显示的行数）

## 使用方法
- **XML**
```java
 <com.liyi.view.FlowView
    android:id="@+id/flowLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

- **代码实现**
```java
1、直接使用BaseAdapter适配器（注：目前FlowView中没有复用机制，所以不需要判断getView（int position，View view，ViewGroup parent）中的view是否为空，进行复用操作）

2、 设置适配器：flowLayout.setAdapter(mAdapter);

例：
private void update2() {
        mList.clear();
        for (int i = 0; i < 4; i++) {
            mList.add("缘分像一道桥");
            mList.add("Get it  on  the  floor");
            mList.add("头文字D");
            mList.add("albert-lii");
            mList.add("be  the  burning fire");
            mList.add("压力背包");
        }
        mAdapter.setData(mList, true);
        // 直接实现FlowView的setAdapter(BaseAdapter adapter)方法即可
        flowLayout.setAdapter(mAdapter);
 }
 

 // item的点击事件
 flowLayout.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(FlowActivity.this, "我是" + position + "号",
                Toast.LENGTH_SHORT).show();
            }
  });
```

## 赞赏
如果你感觉 `FlowView` 帮助到了你，可以点右上角 "Star" 支持一下 谢谢！ ^_^

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


[SUtils]:https://github.com/albert-lii/SUtils
[AutoGridView]:https://github.com/albert-lii/AutoGridView
[demogif]:https://github.com/albert-lii/FlowView/blob/master/screenshot/flowview.gif

