package org.aspect.droid

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class PluginDroid implements Plugin<Project> {
    @Override
    void apply(Project project) {

        def plugins = project.plugins
        def inApp = plugins.withType(AppPlugin)
        def inLib = plugins.withType(LibraryPlugin)
        if (!inApp && !inLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin must be imported first.")
        }

        final def log = project.logger
        final def variants
        if (inApp) {
            variants = project.android.applicationVariants
        } else {
            variants = project.android.libraryVariants
        }

        project.dependencies {
            compile "org.aspectj:aspectjrt:1.9.6"
        }

        variants.all { variant ->
            JavaCompile javaCompile = variant.javaCompile
            javaCompile.doLast {
                String[] args = [
                        "-showWeaveInfo",
                        "-1.5",
                        "-inpath", javaCompile.destinationDir.toString(),
                        "-aspectpath", javaCompile.classpath.asPath,
                        "-d", javaCompile.destinationDir.toString(),
                        "-classpath", javaCompile.classpath.asPath,
                        "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)
                ]
                String[] kotlinArgs = ["-showWeaveInfo",
                                       "-1.5",
                                       "-inpath", project.buildDir.path + "/tmp/kotlin-classes/",
                                       "-aspectpath", javaCompile.classpath.asPath,
                                       "-d", project.buildDir.path + "/tmp/kotlin-classes/",
                                       "-classpath", javaCompile.classpath.asPath,
                                       "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)
                ]

                log.debug "args: " + Arrays.toString(args)
                log.debug "kArgs : " + Arrays.toString(kotlinArgs)

                MessageHandler handler = new MessageHandler(true)
                new Main().run(args, handler)
                new Main().run(kotlinArgs, handler)

                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            break
                        case IMessage.WARNING:
                            log.warn message.message, message.thrown
                            break
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break
                    }
                }
            }
        }
    }
}
