package com.ucsunup.publisher.core

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.AbstractCopyTask
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc

import javax.inject.Inject

@Singleton
class ProjectModifier {

    /**
     * Init project info
     * @param project
     * @return
     */
    def init(Project project) {
        ProjectInfo.instance.init(project)
        MavenConfigurator.instance.initProperties(ProjectInfo.instance.properties)
        SignConfigurator.instance.initProperties(ProjectInfo.instance.properties)
        return this
    }

    /**
     * Start modify target project
     */
    void modify() {
        // Config project
        if (ProjectInfo.instance.isAndroid) {
            configAndroid()
        } else {
            configJava()
        }
        configArtifacts()

        // Config maven
        MavenConfigurator.instance.config(ProjectInfo.instance.targetProject)

        // Config signing
        SignConfigurator.instance.config(ProjectInfo.instance.targetProject)
    }

    /**
     * Target project is android
     */
    void configAndroid() {
        ProjectInfo.instance.targetProject.tasks.create(AndroidJavadocs.sName, AndroidJavadocs.class)
        ProjectInfo.instance.targetProject.tasks.create(AndroidJavadocsJar.sName, AndroidJavadocsJar.class)
        ProjectInfo.instance.targetProject.tasks.create(AndroidSourcesJar.sName, AndroidSourcesJar.class)
    }

    /**
     * Target project is java
     */
    void configJava() {
        ProjectInfo.instance.targetProject.tasks.create(SourcesJar.sName, SourcesJar.class)
        ProjectInfo.instance.targetProject.tasks.create(JavadocJar.sName, JavadocJar.class)
    }

    /**
     * Config artifacts
     */
    void configArtifacts() {
        ProjectInfo.instance.targetProject.artifacts {
            if (ProjectInfo.instance.isAndroid) {
                archives ProjectInfo.instance.targetProject.tasks["androidSourcesJar"]
                archives ProjectInfo.instance.targetProject.tasks["androidJavadocsJar"]
            } else {
                archives ProjectInfo.instance.targetProject.tasks["sourcesJar"]
                archives ProjectInfo.instance.targetProject.tasks["javadocJar"]
            }
        }
    }

    static class AndroidJavadocs extends Javadoc {
        public static final def sName = "androidJavadocs"

        @Override
        FileTree getSource() {
            return ProjectInfo.instance.targetProject.android.sourceSets.main.java.source
        }

        @Override
        FileCollection getClasspath() {
            FileCollection collection = super.getClasspath()
            collection += ProjectInfo.instance.targetProject.files(
                    ProjectInfo.instance.targetProject.android.getBootClasspath().join(File.pathSeparator))
            collection += ProjectInfo.instance.targetProject.configurations.compile
            return collection
        }
    }

    static class AndroidJavadocsJar extends Jar {
        public static final def sName = "androidJavadocsJar"

        @Override
        AbstractCopyTask from(Object... sourcePaths) {
            return ProjectInfo.instance.targetProject.tasks["androidJavadocs"].destinationDir
        }

        @Override
        Set<Object> getDependsOn() {
            return ProjectInfo.instance.targetProject.tasks["androidJavadocs"]
        }

        @Override
        String getClassifier() {
            return "javadoc"
        }
    }

    static class AndroidSourcesJar extends Jar {
        public static final def sName = "androidSourcesJar"

        @Override
        AbstractCopyTask from(Object... sourcePaths) {
            return ProjectInfo.instance.targetProject.android.sourceSets.main.java.source
        }

        @Override
        String getClassifier() {
            return "sources"
        }
    }

    static class SourcesJar extends Jar {
        public static final def sName = "sourcesJar"

        @Inject
        SourcesJar() {
        }

        @Override
        String getName() {
            return sName
        }

        @Override
        AbstractCopyTask from(Object... sourcePaths) {
            return super.from(sourceSets.main.allSource)
        }

        @Override
        Task dependsOn(Object... paths) {
            return ProjectInfo.instance.targetProject.tasks.getByName("classes")
        }

        @Override
        String getClassifier() {
            return "sources"
        }
    }

    static class JavadocJar extends Jar {
        public static final def sName = "javadocJar"

        @Inject
        JavadocJar() {
        }

        @Override
        String getName() {
            return sName
        }

        @Override
        AbstractCopyTask from(Object... sourcePaths) {
            return super.from(javadoc.destinationDir)
        }

        @Override
        Task dependsOn(Object... paths) {
            return ProjectInfo.instance.targetProject.tasks.getByName("javadoc")
        }

        @Override
        String getClassifier() {
            return "javadoc"
        }
    }
}