package com.ucsunup.publisher.core

import org.gradle.api.Project

abstract class BaseConfigurator {

    abstract def initProperties(Properties properties)

    abstract def config(Project project)
}