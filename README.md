## NeRecyclerView 带有悬浮条的RecyclerView + 自定义CircleImageView
用CoordinateLayout + RecyclerView实现的带有悬浮条效果的demo, 并且用到了自定义CircleImageView  
### 思路：
帧布局包裹RecyclerView和RecyclerView的Title布局（悬浮条），滚动时监听下一个Item的title布局到顶部的位置，
从而设置悬浮条的向上滚动和悬浮静止以及其数据的更新
### 示例效果如下图所示：  
![image](https://github.com/tianyalu/NeRecyclerView/blob/master/show/show.gif)
