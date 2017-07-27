# LoginConcept

A picture is worth a thousand words. Here you have something better:

![](https://github.com/vpaliyX/LoginConcept/blob/master/art/ezgif.com-video-to-gif(19).gif)__
![](https://github.com/vpaliyX/LoginConcept/blob/master/art/ezgif.com-video-to-gif(20).gif)
### More smooth is [here](https://www.youtube.com/watch?v=18MwhJj97gQ) ###

Or you can have with rectangular corners:

![](https://github.com/vpaliyX/LoginConcept/blob/master/art/final_resized.gif)

If you want this option, just change [this file](https://github.com/vpaliyX/LoginConcept/blob/master/app/src/main/res/drawable/circle.xml) as shown below:

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners android:radius="50dp"/>  <!-- Change this line here to 0dp -->
    <stroke android:color="#87eeeeee"/>
    <solid android:color="#87eeeeee"/>
</shape>
```

I created a post about the implementation on [Medium](https://medium.com/@vpaliy/do-you-dare-me-to-implement-this-login-screen-bf29b72d9e39), you can check this out for more details.

Inspired by [Yaroslav Zubkov's](https://www.uplabs.com/posts/7-2-log-in-sign-up) design.
