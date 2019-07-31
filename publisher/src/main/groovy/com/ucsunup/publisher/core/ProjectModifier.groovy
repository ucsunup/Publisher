package com.ucsunup.publisher.core

import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

@Singleton
class ProjectModifier {

    public static final def TASK_ANDROIDJAVADOC = "androidJavadoc"
    public static final def TASK_ANDROIDJAVADOCJAR = "androidJavadocJar"
    public static final def TASK_ANDROIDSOURCESJAR = "androidSourcesJar"
    public static final def TASK_SOURCESJAR = "sourcesJar"
    public static final def TASK_JAVADOCJAR = "javadocJar"
    public static final def TASK_GROOVYDOCJAR = "groovydocJar"

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
        configOthers()

        // Config maven
        MavenConfigurator.instance.config(ProjectInfo.instance.targetProject)

        // Config signing
        SignConfigurator.instance.config(ProjectInfo.instance.targetProject)
    }

    void configAndroid() {
        // config task
        ProjectInfo.instance.targetProject.tasks.register(TASK_ANDROIDJAVADOC, javadoc.class) {
            source = ProjectInfo.instance.targetProject.android.sourceSets.main.java.srcDirs
            classpath += ProjectInfo.instance.targetProject.files(
                    ProjectInfo.instance.targetProject.android.getBootClasspath().join(File.pathSeparator))
            classpath += ProjectInfo.instance.targetProject.configurations.compile
        }
        ProjectInfo.instance.targetProject.tasks.register(TASK_ANDROIDJAVADOCJAR, Jar.class) {
            classifier = 'javadoc'
            dependsOn TASK_ANDROIDJAVADOC
            from ProjectInfo.instance.targetProject.tasks[TASK_ANDROIDJAVADOC].destinationDir
        }
        ProjectInfo.instance.targetProject.tasks.register(TASK_ANDROIDSOURCESJAR, Jar.class) {
            classifier = 'sources'
            from ProjectInfo.instance.targetProject.android.sourceSets.main.java.source
        }
    }

    void configJava() {
        // config task
        ProjectInfo.instance.targetProject.tasks.register(TASK_SOURCESJAR, Jar.class) {
            classifier = 'sources'
            dependsOn 'classes'
            from ProjectInfo.instance.targetProject.sourceSets.main.allSource
        }
        ProjectInfo.instance.targetProject.tasks.register(TASK_JAVADOCJAR, Jar.class) {
            classifier = 'javadoc'
            dependsOn 'javadoc'
            from ProjectInfo.instance.targetProject.javadoc.destinationDir
        }
        ProjectInfo.instance.targetProject.tasks.register(TASK_GROOVYDOCJAR, Jar.class) {
            classifier = 'groovydoc'
            dependsOn 'groovydoc'
            from ProjectInfo.instance.targetProject.groovydoc.destinationDir
        }
    }

    /**
     * Config artifacts
     */
    void configArtifacts() {
//        ProjectInfo.instance.targetProject.artifacts {
//            if (ProjectInfo.instance.isAndroid) {
//                archives ProjectInfo.instance.targetProject.tasks[AndroidSourcesJar.sName]
//                archives ProjectInfo.instance.targetProject.tasks[AndroidJavadocsJar.sName]
//            } else {
//                archives ProjectInfo.instance.targetProject.tasks[SourcesJar.sName]
//                archives ProjectInfo.instance.targetProject.tasks[JavadocJar.sName]
//            }
//        }
    }

    /**
     * Config others
     */
    void configOthers() {
    }
}