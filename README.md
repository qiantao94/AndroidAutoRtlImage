# Android Auto RTL Image [![](https://jitpack.io/v/qiantao94/AndroidAutoRtlImage.svg)](https://jitpack.io/#qiantao94/AndroidAutoRtlImage)
Automatically mirror the ImageView's src or View's background in the RTL layout direction.
## What?
Add `app:mirrorSrc="true"` or `app:mirrorBackground="true"` in xml files, resource's drawable will be auto mirrored. No need for extra mirror image files in ldrtl directory.
```xml
<ImageView
    ...
    android:src="@mipmap/ic_launcher_round"
    app:mirrorSrc="true" />

<TextView
    ...
    android:background="@drawable/bg_chat"
    app:mirrorBackground="true" />
```
Don't forget init this library in application. `RtlImageLibrary.inject(this)`
```kotlin
class RtlApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RtlImageLibrary.inject(this)
    }
}
```
## Download
1. Add the JitPack repository to your build file
```gradle
// Gradle < 6.8, root build.gradle file
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// Gradle >= 6.8 settings.gradle file
dependencyResolutionManagement {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency
```gradle
dependencies {
  implementation 'com.github.qiantao94:AndroidAutoRtlImage:0.0.1'
}
```
## Custom View ?
If you have custom view, this library's key code is `drawable.setAutoMirrored(true)`, use it!

## Known Issues
May conflict with other libraries that use `LayoutInflater.Factory2`
