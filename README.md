## NeRecyclerView 带有悬浮条的RecyclerView + 自定义CircleImageView + 自定义Behavior
用CoordinateLayout + RecyclerView实现的带有悬浮条效果的demo, 并且用到了自定义CircleImageView  
### 悬浮条思路：
帧布局包裹RecyclerView和RecyclerView的Title布局（悬浮条），滚动时监听下一个Item的title布局到顶部的位置，
从而设置悬浮条的向上滚动和悬浮静止以及其数据的更新。  

### 自定义Behavior
实现FloatingActionBar的上滑缩放隐藏和下拉缩放显示以及点击回到顶部功能   
tips:  
Behavior是Android Support Design库里面新增的布局概念，主要的作用是用来协调CoordinatorLayout
里面直接Child Views之间交互行为的.特别要注意的点是Behavior只能作用于CoordinatorLayout的直接Child View.  
参考：[Android CoordinatorLayout Behavior](https://www.jianshu.com/p/4ebb7bfa1228)
### 示例效果如下图所示：  
![image](https://github.com/tianyalu/NeRecyclerView/blob/master/show/show.gif)
