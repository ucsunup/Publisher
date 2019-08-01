package com.ucsunup.publisher.core

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin

@Singleton
class MavenConfigurator extends BaseConfigurator {

    @Override
    def initProperties(Properties properties) {
        MavenPublicationProperties.instance.init(properties)
        MavenRepositoryProperties.instance.init(properties)
        return this
    }

    @Override
    def config(Project project) {
        project.plugins.apply(MavenPublishPlugin)

        PublishingExtension publishingExtension = project.extensions["publishing"]
        publishingExtension.publications {
            try {
                mavenJava(MavenPublication) {
                    groupId = MavenPublicationProperties.instance.groupId
                    artifactId = MavenPublicationProperties.instance.artifactId
                    version = MavenPublicationProperties.instance.version

                    if (ProjectInfo.instance.isAndroid) {
                        configurations project.configurations.archives
                        artifact project.tasks[ProjectModifier.TASK_ANDROIDJAVADOCJAR]
                        artifact project.tasks[ProjectModifier.TASK_ANDROIDSOURCESJAR]
                    } else {
                        from project.components.java
                        artifact project.tasks[ProjectModifier.TASK_SOURCESJAR]
                        artifact project.tasks[ProjectModifier.TASK_JAVADOCJAR]
                        if (project.plugins.hasPlugin("groovy")) {
                            artifact project.tasks[ProjectModifier.TASK_GROOVYDOCJAR]
                        }
                    }
//                    versionMapping {
//                        usage("java-api") {
//                            fromResolutionOf("runtimeClasspath")
//                        }
//                        usage("java-runtime") {
//                            fromResolutionResult()
//                        }
//                    }
                    pom {
                        name.set(MavenPublicationProperties.instance.pomName)
                        description.set(MavenPublicationProperties.instance.pomDescription)
                        url.set(MavenPublicationProperties.instance.pomUrl)
                        licenses {
                            license {
                                name.set(MavenPublicationProperties.instance.pomLicensesLicenseName)
                                url.set(MavenPublicationProperties.instance.pomLicensesLicenseUrl)
                                distribution.set(MavenPublicationProperties.instance.pomLicensesLicenseDistribution)
                            }
                        }
                        developers {
                            developer {
                                id.set(MavenPublicationProperties.instance.pomDevelopersDeveloperId)
                                name.set(MavenPublicationProperties.instance.pomDevelopersDeveloperName)
                                email.set(MavenPublicationProperties.instance.pomDevelopersDeveloperEmail)
                            }
                        }
                        scm {
                            url.set(MavenPublicationProperties.instance.pomScmUrl)
                            connection.set(MavenPublicationProperties.instance.pomScmConnection)
                            developerConnection.set(MavenPublicationProperties.instance.pomScmDeveloperConnection)
                        }
                    }
                }
            } catch (Exception e) {
                println("publications: " + e)
            }
        }
        publishingExtension.repositories {
            maven {
                try {
                    // change URLs to point to your repos, e.g. http://my.org/repo
                    url = project.uri(MavenPublicationProperties.instance.version.endsWith('SNAPSHOT')
                            || MavenPublicationProperties.instance.version.endsWith('snapshot')
                            ? MavenRepositoryProperties.instance.uriSnapshot
                            : MavenRepositoryProperties.instance.uriRelease)
                    // authenticate
                    if ("http".equalsIgnoreCase(url.getScheme())
                            || "https".equalsIgnoreCase(url.getScheme())) {
                        credentials {
                            username MavenRepositoryProperties.instance.username
                            password MavenRepositoryProperties.instance.password
                        }
                    }
                } catch (Exception e) {
                    println("repositories: " + e)
                }
            }
        }

        Task repositoryTask = project.tasks.getByName("publishMavenJavaPublicationToMavenRepository")
        project.tasks.register("publishToMaven") {
            dependsOn repositoryTask.name
            group "Publisher"
            description repositoryTask.description
        }
        Task localTask = project.tasks.getByName("publishMavenJavaPublicationToMavenLocal")
        project.tasks.register("publishToMavenL") {
            dependsOn localTask.name
            group "Publisher"
            description localTask.description
        }
    }
}