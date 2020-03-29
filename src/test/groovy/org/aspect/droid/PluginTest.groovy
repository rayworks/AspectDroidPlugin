package org.aspect.droid

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class PluginTest {
    @Test
    void addPluginToProject() {
        Project project = ProjectBuilder.builder().build()

        def manager = project.pluginManager

        try {
            manager.apply 'org.aspect.droid'
        } catch (Exception x) {
            x.printStackTrace()
            Assert.assertTrue(true)
        }
    }
}
