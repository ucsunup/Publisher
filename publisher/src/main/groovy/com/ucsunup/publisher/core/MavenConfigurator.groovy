package com.ucsunup.publisher.core

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
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
        ProjectInfo.instance.targetProject.plugins.apply(MavenPublishPlugin)
        ProjectInfo.instance.targetProject.tasks.getByName("publish").setGroup("Publisher")
        ProjectInfo.instance.targetProject.tasks.getByName("publishToMavenLocal").setGroup("Publisher")

        PublishingExtension publishingExtension = ProjectInfo.instance.targetProject.extensions.getByName("publishing")
        publishingExtension.publications {
            try {
                mavenJava(org.gradle.api.publish.maven.MavenPublication) {
                    groupId = MavenPublicationProperties.instance.groupId
                    artifactId = MavenPublicationProperties.instance.artifactId
                    version = MavenPublicationProperties.instance.version

                    from ProjectInfo.instance.targetProject.components.java
//                    artifact ProjectInfo.instance.targetProject.tasks[ProjectModifier.SourcesJar.sName]
//                    artifact ProjectInfo.instance.targetProject.tasks[ProjectModifier.JavadocJar.sName]
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
//        publishingExtension.repositories.add(this.mavenRepository)
        publishingExtension.repositories {
            maven {
                try {
                    // change URLs to point to your repos, e.g. http://my.org/repo
                    url = ProjectInfo.instance.targetProject.uri(MavenPublicationProperties.instance.version.endsWith('SNAPSHOT')
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
    }
}