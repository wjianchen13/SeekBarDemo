# SeekBar使用
实现文字跟随SeekBar滑动条滑动  
max=100,代表它的取值范围是0-100,共101个值;  
progress=20,代表默认值是20  
progressDrawable,表示SeekBar的背景图片  
thumbe,表示SeekBar的滑块图片   

# Seekbar背景和进度高度不对  
自定义了进度之后，高度在android:thumb设置不起作用，在xml设置最大高度  
```Java
android:maxHeight="6dp"
```

# 去掉边距
seekbar默认两边会有间隙，去掉间隙：  
```Java
android:paddingStart="0dp"
android:paddingEnd="0dp"
```
或者java代码中设置padding    
```Java
setPadding(0,0,0,0)
```
这里应该尽可能保留一定的padding值，否则滑块移动到0和最大值的时候都会有一半被遮住，应至少保留滑块款都的一半大小。  

# SeekBar与滑块后出现空白间隔   
SeekBar内添加一句  
```Java
android:splitTrack="false"
```

# 去掉SeekBar thumb和按下效果  
加上如下属性  
```Java
android:thumb="@null"
android:duplicateParentState="true"
```

# 参考文档
Seekbar一个比较好用的库
https://github.com/litao0621/nifty-slider



