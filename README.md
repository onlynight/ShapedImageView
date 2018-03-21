# ShapedImageView
shaped image view library
This is a shaped image view library, you can simply use this library with gradle.
You can simply use the default shape in the library, or you can define your own shape to display.
This library is a way teach you to make the shaped image view, you can use the code anywhere you want.

## Step 1. 
Add the JitPack repository to your build file,Add it in your root build.gradle at the end of repositories:

### Gradle

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

	
## Step 2. 
Add the dependency

```groovy
dependencies {
        compile 'com.github.onlynight:ShapedImageView:1.0.3'
}
```

## Demo1
You can simple use it in the xml layout file;
```xml
<com.github.onlynight.chatimageview.ChatImageView
    android:id="@+id/chatImageView"
    android:layout_width="200dp"
    android:layout_height="150dp"
    android:scaleType="centerCrop"
    android:src="@drawable/ic_pic_bg"
    app:corner_radius="4dp"
    app:round_corner="true"
    app:sharp_corner_direction="left"
    app:sharp_corner_height="10dp"
    app:sharp_corner_start="20dp"
    app:sharp_corner_width="8dp" />
```

## Demo2
or you can set the attrs with your code, that's simple.
```java
public class MainActivity extends AppCompatActivity {

    private ChatImageView chatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatImageView = (ChatImageView) findViewById(R.id.chatImageView);
        chatImageView.setSharpCornerStart(Utils.dpToPx(100));
        chatImageView.setCornerRadius(Utils.dpToPx(20));
        chatImageView.setSharpCornerDirection(ChatImageView.SHARP_CORNER_DIRECTION_RIGHT);
    }
}
```

### Screenshot
![shaped_image_view](https://github.com/onlynight/ShapedImageView/blob/master/screenshot/Screenshot_2015-12-11-18-16-46.png)
![shaped_image_view](https://github.com/onlynight/ShapedImageView/blob/master/screenshot/Screenshot_2015-12-11-18-16-56.png)
![shaped_image_view](https://github.com/onlynight/ShapedImageView/blob/master/screenshot/Screenshot_2015-12-11-18-17-00.png)
![shaped_image_view](https://github.com/onlynight/ShapedImageView/blob/master/screenshot/Screenshot_2015-12-11-18-17-03.png)
