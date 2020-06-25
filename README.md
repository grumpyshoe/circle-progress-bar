
# Circle Progress Bar

![minSDK 23](https://img.shields.io/badge/minSDK-API_23-green.svg?style=flat)
  ![targetSDK 29](https://img.shields.io/badge/targetSDK-API_29-blue.svg)

Because I'm tired of not having a progress bar in ring form that correctly displays the progress state, this little project was created.

It is only a progress bar in form of a ring...who would have thought it :)

The view can be set directly in the xml and currently only contains 3 attributes:
 - `app:max` - the value that represents 360 degrees of the ring
- `tapp:progressBackgroundTint` - The color value for the progress background
- `app:progressTint`- the color value for the progress display


## Installation

and add `jitpack`to your repositories
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
    ...
}
```

Add this dependency to your app _build.gradle_ and apply the plugin at the bottom:
```gradle
implementation 'com.github.grumpyshoe:circle-progress-bar:1.1.1'
```


## Usage

Add the `CirlceProgressBar` to your xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    ... 

    <com.grumpyshoe.circleprogressbar.CircleProgressBar
        android:id="@+id/circleProgressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:max="20"
        app:progressBackgroundTint="@color/grey"
        app:progressTint="@color/blue" />


   ....
</androidx.constraintlayout.widget.ConstraintLayout>
```

Set the max value (if not done in xml)
```kotlin
circleProgressBar.setMax(20)
```
Update the progress value
```kotlin
 circleProgressBar.updateProgress(value)
```


## Need Help or something missing?

Please [submit an issue](https://github.com/grumpyshoe/circle-progress-bar/issues) on GitHub.


## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE) file.


#### Build Environment
```
Android Studio 4.0
Build #AI-193.6911.18.40.6514223, built on May 20, 2020
Runtime version: 1.8.0_242-release-1644-b3-6222593 x86_64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
macOS 10.15.5
```