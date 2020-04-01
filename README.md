# AspectDroidPlugin [ ![Download](https://api.bintray.com/packages/crayzhou/maven/AspectDroidPlugin/images/download.svg?version=1.0.1) ](https://bintray.com/crayzhou/maven/AspectDroidPlugin/1.0.1/link)

A gradle plugin based on [AspectJ](https://en.wikipedia.org/wiki/AspectJ) supports [AOP](https://en.wikipedia.org/wiki/Aspect-oriented_programming) on Android module conveniently.

## usage

* Configure your repository for the plugin
```
repositories {
    maven {
        url  "https://dl.bintray.com/crayzhou/maven"
    }
}
```

* Specify the dependency in your root build.gradle file:
```
dependencies {
    classpath 'org.aspect.droid:plugin:1.0.1'
}
```

* Apply the plugin in your module
```
apply plugin: 'org.aspect.droid'
``` 
