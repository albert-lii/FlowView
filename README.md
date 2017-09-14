# FlowView
FlowView是一款流布局控件，采用Adapter模式，可以自定义item，更加灵活，使用起来就和ListView与BaseAdapter搭配一样简单，提供各种对齐方式，可以自适应
高度，也可以设置item的高度，可以控制item的最大显示行数...

## 联系方式
> 电子邮箱：albertlii@163.com

## 推荐
- [AutoGridView](https://github.com/albert-lii/AutoGridView) 宫格控件，QQ空间九宫格、普通宫格模式、点击添加照片...
- [Sutils](https://github.com/albert-lii/SUtils) 轻量的常用的工具类库

## 演示
先来看看效果吧！  

![演示](https://github.com/albert-lii/FlowView/blob/master/screenshot/flowview.gif)  

> **博客详情：http://blog.csdn.net/liyi1009365545/article/details/77963829**

## 添加依赖
```Java
Step 1:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

Step 2:

    dependencies {
            compile 'com.github.albert-lii:FlowView:1.0.4'
    }
```

## 自定义属性
```Java
    <!--流布局控件-->
    <declare-styleable name="FlowView">
        <!-- item 在一行中的横向对齐方式-->
        <attr name="flow_horalign">
            <!-- item 左对齐-->
            <enum name="left" value="0" />
            <!-- item 居中对齐-->
            <enum name="middle" value="1" />
            <!-- item 右对齐-->
            <enum name="right" value="2" />
        </attr>
        <!-- item 在一行中的纵向对齐方式，当设置 flow_height 时，此属性无效-->
        <attr name="flow_vertalign">
            <enum name="top" value="0" />
            <enum name="middle" value="1" />
            <enum name="bottom" value="2" />
        </attr>
        <!--流布局的 item 的高度-->
        <attr name="flow_height" format="dimension" />
        <!--流布局的 item 横向间距-->
        <attr name="flow_hspace" format="dimension" />
        <!--流布局的 item 纵向间距-->
        <attr name="flow_vspace" format="dimension" />
        <!--流布局最多显示的行数-->
        <attr name="flow_maxrows" format="integer" />
    </declare-styleable>
```

## 使用方法
- **XML**
```Java
 <com.liyi.flowview.FlowView
    android:id="@+id/flowLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="10dp" />
```

- **代码实现**
```Java
// 必须继承BaseFlowAdapter类
public class FlowAdapter extends BaseFlowAdapter {
    private ArrayList<String> mList;
    private boolean showImg;

    public FlowAdapter() {

    }

    public void setData(ArrayList<String> list, boolean showImg) {
        this.mList = list;
        this.showImg = showImg;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    // 可自定义item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ...
    }
}


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
        // 直接实现FlowView的setAdapter(BaseFlowAdapter adapter)方法即可
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
