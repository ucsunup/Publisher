package com.ucsunup.publisher.core

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin

@Singleton
class SignConfigurator extends BaseConfigurator {

    /**
     * Init properties
     * @param properties
     * @return
     */
    @Override
    def initProperties(Properties properties) {
        SignProperties.instance.init(properties)
    }

    /**
     * Start config
     * @param project
     */
    @Override
    def config(Project project) {
        println("Sign Config: " + SignProperties.instance.needSign)
        if (SignProperties.instance.needSign) {
            project.plugins.apply(SigningPlugin)
            // Sign target artifact
            SigningExtension signingExtension = project.extensions["signing"]
            PublishingExtension publishingExtension = project.extensions["publishing"]
//            if (ProjectInfo.instance.isAndroid) {
//                signingExtension.sign(project.configurations.archives)
//            } else {
//                signingExtension.sign(ProjectInfo.instance.targetProject.tasks["jar"])
//                signingExtension.sign(project.tasks[ProjectModifier.SourcesJar.sName])
//                signingExtension.sign(project.tasks[ProjectModifier.JavadocJar.sName])
//                signingExtension.sign(project.tasks[ProjectModifier.GroovydocJar.sName])
//            }
            signingExtension.sign(publishingExtension.publications.mavenJava)

            // Set config
            project.gradle.taskGraph.whenReady { taskGraph ->
                if (taskGraph.allTasks.any { it instanceof Sign }) {
                    project.allprojects {
                        project.ext."signing.keyId" = SignProperties.instance.keyId
                        project.ext."signing.secretKeyRingFile" = SignProperties.instance.secretKeyRingFile
                        project.ext."signing.password" = SignProperties.instance.password
                    }
                }
            }
        }
    }
}