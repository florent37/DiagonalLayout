# Diagonalify
####Create diagonal cuts on image view 

#### Min SDK 9

![Banner](art/banner.png)
## Screnshots

![Screenshot](art/screenshot_1.png)					![Screenshot](art/screenshot_2.png)

## Usage
Add these lines in your build.gradle file at root level:

```xml
repositories {
        jcenter()
				maven { url "https://jitpack.io" }
}
```

```xml
Add these lines in your build.gradle file at root level:
dependencies {
    compile 'com.github.developer-shivam:Diagonalify:1.1'    
}
```

## Basic Usage
###Java
```java

		DiagonalView diagonalView = new DiagonalView(this);
    diagonalView.setAngle(15);
    diagonalView.setDiagonalGravity(DiagonalView.LEFT);
		diagonlaView.setDiagonalColor(Color.BLACK);
    diagonalView.setBackgroundColor(Color.WHITE);

```
###XML
```xml
 
    <developer.shivam.library.DiagonalView android:id="@+id/diagonal_view"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:src="@drawable/cover_photo"
        android:scaleType="centerCrop"
        diagonal:diagonalColor="#FFFFFF"
        diagonal:backgroundColor="#00F44336"
        diagonal:diagonalGravity="right"
        diagonal:angle="15"/>

```

## Custom Attributes

### Diagonal Color
```xml
        diagonal:diagonalColor="#FFFFFF"
```
### Background Color 
```xml
        diagonal:backgroundColor="#50F44336"
```
### Diagonal Angle
```xml
        diagonal:angle="15"
```

## License
Copyright (c) 2016 Shivam Satija

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
