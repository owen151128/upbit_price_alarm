# upbit_price_alarm
# Overview
<img src="https://github.com/owen151128/upbit_price_alarm/blob/master/imgs/main.png?raw=true" width="40%" height="40%" />


Alarm for upbit cryptocurrency price

## Download Apk(Release)
v0.1 : [upbit_price_alarm_signed.apk](https://github.com/owen151128/upbit_price_alarm/raw/master/apk/upbit_price_alarm_signed.apk)

## Compatibility
upbit_price_alarm works on Android!

## Usage
Try install upbit_price_alarm(apk) on your android device

Just start **upbit_price_alarm**

## Project Structure : 
```
├── app
│   ├── build.gradle
│   ├── libs
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       │   └── java
│       │       └── kr
│       │           └── owens
│       │               └── upa
│       │                   └── ExampleInstrumentedTest.kt
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── ic_launcher-playstore.png
│       │   ├── java
│       │   │   └── kr
│       │   │       └── owens
│       │   │           └── upa
│       │   │               ├── app
│       │   │               │   └── App.kt
│       │   │               ├── helper
│       │   │               │   ├── AlarmHelper.kt
│       │   │               │   └── TickerHelper.kt
│       │   │               ├── main
│       │   │               │   └── MainActivity.kt
│       │   │               ├── model
│       │   │               │   ├── Price.kt
│       │   │               │   └── Ticker.kt
│       │   │               ├── network
│       │   │               │   ├── BaseService.kt
│       │   │               │   ├── UpbitApi.kt
│       │   │               │   └── UpbitService.kt
│       │   │               └── service
│       │   │                   ├── Actions.kt
│       │   │                   ├── UpbitPriceAlarmService.kt
│       │   │                   └── UpbitPriceNotification.kt
│       │   └── res
│       │       ├── drawable
│       │       │   ├── ic_launcher_background.xml
│       │       │   ├── ic_launcher_foreground.xml
│       │       │   └── splash_theme.xml
│       │       ├── drawable-v24
│       │       ├── layout
│       │       │   ├── activity_main.xml
│       │       │   └── list_item.xml
│       │       ├── mipmap-anydpi-v26
│       │       │   ├── ic_launcher.xml
│       │       │   └── ic_launcher_round.xml
│       │       ├── mipmap-hdpi
│       │       │   ├── ic_launcher.png
│       │       │   └── ic_launcher_round.png
│       │       ├── mipmap-mdpi
│       │       │   ├── ic_launcher.png
│       │       │   └── ic_launcher_round.png
│       │       ├── mipmap-xhdpi
│       │       │   ├── ic_launcher.png
│       │       │   └── ic_launcher_round.png
│       │       ├── mipmap-xxhdpi
│       │       │   ├── ic_launcher.png
│       │       │   └── ic_launcher_round.png
│       │       ├── mipmap-xxxhdpi
│       │       │   ├── ic_launcher.png
│       │       │   └── ic_launcher_round.png
│       │       ├── values
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── values-night
│       │           └── themes.xml
│       └── test
│           └── java
│               └── kr
│                   └── owens
│                       └── upa
│                           └── ExampleUnitTest.kt
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── imgs
│   └── main.png
├── local.properties
└── settings.gradle
```

## Library used : 
- kotlinx-coroutines-android -> for async
- gson -> for parase json
- retrofit2 -> for connect upbit api
- converter-gson -> for json -> kotlin data class

## Build :
first, go to project root dir.
- Clean -> gradle clean
- build -> gradle assemble[Debug / Release]

build process requried android-sdk!
