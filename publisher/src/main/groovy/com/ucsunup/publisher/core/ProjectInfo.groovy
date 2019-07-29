package com.ucsunup.publisher.core

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Project

@Singleton
class ProjectInfo {

    /**
     * Target project
     */
    private Project targetProject
    /**
     * Indicator target project if is android project
     */
    private def isAndroid
    /**
     * Properties
     */
    private def properties

    def init(Project project) {
        this.targetProject = project
        this.isAndroid = project.plugins.hasPlugin(AppPlugin) || project.plugins.hasPlugin(LibraryPlugin)
        this.properties = new Properties()
        this.properties.load(targetProject.rootProject.file('local.properties').newDataInputStream())
    }

    Project getTargetProject() {
        return targetProject
    }

    def getIsAndroid() {
        return isAndroid
    }

    def getProperties() {
        return properties
    }
}