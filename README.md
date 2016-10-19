# DiagonalLayout

With Diagonal Layout explore new styles and approaches on material design

[![screen](https://raw.githubusercontent.com/florent37/DiagonalLayout/master/media/sample.gif)](https://www.github.com/florent37/DiagonalLayout)

# Usage

```xml
<com.github.florent37.diagonallayout.DiagonalLayout
        android:layout_width="match_parent"
        android:layout_height="250dp"
        diagonal:diagonal_angle="10"
        diagonal:diagonal_gravity="left|right|top|bottom"
        android:elevation="10dp"
        >

        <!-- YOUR CONTENT -->

</com.github.florent37.diagonallayout.DiagonalLayout>
```

# Sample

[![screen](https://raw.githubusercontent.com/florent37/DiagonalLayout/master/media/sample2_elevation.png)](https://www.github.com/florent37/DiagonalLayout)

```xml
<com.github.florent37.diagonallayout.DiagonalLayout
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:elevation="10dp"
        app:diagonal_angle="20"
        app:diagonal_gravity="right">

        <ImageView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scaleType="centerCrop"
            android:src="@drawable/mountains" />

</com.github.florent37.diagonallayout.DiagonalLayout>
```

# Elevation

**Do not forget to add elevation with** `android:elevation="**dp"`

[![screen](https://raw.githubusercontent.com/florent37/DiagonalLayout/master/media/with_elevation_small.png)](https://www.github.com/florent37/DiagonalLayout)

# KenBurns

Using [https://github.com/flavioarfaria/KenBurnsView](https://github.com/flavioarfaria/KenBurnsView)

[![screen](https://raw.githubusercontent.com/florent37/DiagonalLayout/master/media/sample.gif)](https://www.github.com/florent37/DiagonalLayout)

```xml
<com.github.florent37.diagonallayout.DiagonalLayout
        android:id="@+id/diagonalLayout"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        diagonal:diagonal_angle="10"
        diagonal:diagonal_gravity="left"
        android:elevation="30dp"
        >

        <com.flaviofaria.kenburnsview.KenBurnsView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scaleType="centerCrop"
            android:src="@drawable/background"
            />

        ...

</com.github.florent37.diagonallayout.DiagonalLayout>
```

#Inspirations

Exploration of some new styles and approaches on material design. This one here is a user profile for Hollywood personas

[https://material.uplabs.com/posts/user-profile-concept](https://material.uplabs.com/posts/user-profile-concept)

[![screen](https://raw.githubusercontent.com/florent37/DiagonalLayout/master/media/materialup.png)](https://material.uplabs.com/posts/user-profile-concept)

#Download

In your module [![Download](https://api.bintray.com/packages/florent37/maven/DiagonalLayout/images/download.svg)](https://bintray.com/florent37/maven/DiagonalLayout/_latestVersion)
```groovy
compile 'com.github.florent37:diagonallayout:1.0.1  '
```

#Logs

##1.0.1

- Added Elevation

##1.0.2

- Can setup the diagonal on *top* or *bottom*
- Fixed angle calculation
- Added flag attributes `top|bottom|left|right`

#Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2016 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
