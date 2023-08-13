# Android-Horizontal-Slider
[![Version](https://jitpack.io/v/longdt57/Android-Vertical-Slider.svg)](https://github.com/longdt57/Android-Vertical-Slider/releases)
These Sliders base on Material Library version 1.9.0

## ScreenShots
| Vertical Slider | Horizontal Middle Slider |
|-|-|
| <img src="screenshots/screenshot_vertical_slider.png" width=300 /> | <video src="screenshots/screenshot_slider_middle_point.png" width=300 /> |

## Implementation

### build.gradle
```
repositories {
  maven { url "https://jitpack.io" }
}

```
### [module]/build.gradle

```

dependencies {
  implementation 'com.github.longdt57:Android-Vertical-Slider:{version}'
}
```

## Usage
### 1. VerticalSlider
```
<lee.module.slider.vertical.VerticalSlider
    android:id="@+id/slider"
    android:layout_width="wrap_content"
    android:layout_height="300dp"
    android:stepSize="1"
    android:value="50"
    android:valueFrom="0"
    android:valueTo="100"
    app:thumbColor="@color/black"
    app:tickColor="@android:color/transparent"
    app:trackColorActive="@android:color/black"
    app:trackColorInactive="#66FFFFFF" />
```
- Set maxHeight by: verticalSlider.maxHeight = 1000
- Set upSideDown: setUpsideDown(true)
