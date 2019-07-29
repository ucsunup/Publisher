package com.ucsunup.publisher

import com.ucsunup.publisher.core.ProjectModifier
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * PublisherPlugin
 */
class PublisherPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        ProjectModifier.instance.init(project).modify()
    }
}