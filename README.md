# LoginConcept
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

A picture is worth a thousand words. Here you have something better:

![](https://github.com/vpaliyX/LoginConcept/blob/master/art/ezgif.com-video-to-gif(19).gif)__
![](https://github.com/vpaliyX/LoginConcept/blob/master/art/ezgif.com-video-to-gif(20).gif)
### More smooth is [here](https://www.youtube.com/watch?v=p83w7OPfpFI) ###

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

Also, you can download it:

<a href="https://play.google.com/store/apps/details?id=com.vpaliy.loginconcept">
<img src="https://github.com/vpaliyX/LoginConcept/blob/master/art/get_it.png" />
</a>


Inspired by [Yaroslav Zubkov's](https://www.uplabs.com/posts/7-2-log-in-sign-up) design.

```
MIT License

Copyright (c) 2017 Vasyl Paliy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
