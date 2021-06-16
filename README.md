# AspectDroidPlugin

A gradle plugin based on [AspectJ](https://en.wikipedia.org/wiki/AspectJ) supports [AOP](https://en.wikipedia.org/wiki/Aspect-oriented_programming) on Android module conveniently.

## Usage

* Configure your repository for the plugin
```
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

* Specify the dependency in your root build.gradle file:
```
dependencies {
    classpath 'com.github.rayworks:AspectDroidPlugin:0.1.1'
}
```

* Apply the plugin in your module
```
apply plugin: 'org.aspect.droid'
```

## Note

For the compatibility with Kotlin code, please define the `Aspect` class in Kotlin.
You also can check the sample project in [here](https://github.com/rayworks/DroidWeekly/tree/aop_test).

## Credits

* [aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)

* [dhaspject](https://github.com/dikeboy/dhaspject)

* [Hugo by Jarke Wharton](https://github.com/JakeWharton/hugo)

