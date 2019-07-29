package com.ucsunup.publisher.core

import org.gradle.api.DefaultTask

class BaseTask extends DefaultTask {
    def GROUP_NAME = "Publisher"

    @Override
    String getGroup() {
        return GROUP_NAME
    }

    void generatePom() {
    }
}