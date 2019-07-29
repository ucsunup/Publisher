package com.ucsunup.publisher.core

@Singleton
class MavenRepositoryProperties {
    /**
     * Config: Snapshot uri
     */
    private def KEY_MAVEN_URI_SNAPSHOT = "maven.uri.snapshot"
    /**
     * Config: Release uri
     */
    private def KEY_MAVEN_URI_RELEASE = "maven.uri.release"
    /**
     * Config: Maven name
     */
    private def KEY_MAVEN_NAME = "maven.name"
    /**
     * Config: Maven username, if repository need authenticate
     */
    private def KEY_MAVEN_USERNAME = "maven.username"
    /**
     * Config: Maven password, if repository need authenticate
     */
    private def KEY_MAVEN_PASSWORD = "maven.password"

    private def uriSnapshot
    private def uriRelease
    private def name
    private def username
    private def password

    def init(Properties properties) {
        if (properties.containsKey(KEY_MAVEN_URI_SNAPSHOT)) {
            setUriSnapshot(properties.getProperty(KEY_MAVEN_URI_SNAPSHOT))
        }
        if (properties.containsKey(KEY_MAVEN_URI_RELEASE)) {
            setUriRelease(properties.getProperty(KEY_MAVEN_URI_RELEASE))
        }
        if (properties.containsKey(KEY_MAVEN_NAME)) {
            setName(properties.getProperty(KEY_MAVEN_NAME))
        }
        if (properties.containsKey(KEY_MAVEN_USERNAME)) {
            setUsername(properties.getProperty(KEY_MAVEN_USERNAME))
        }
        if (properties.containsKey(KEY_MAVEN_PASSWORD)) {
            setPassword(properties.getProperty(KEY_MAVEN_PASSWORD))
        }
    }

    def getUriSnapshot() {
        return uriSnapshot
    }

    void setUriSnapshot(uriSnapshot) {
        this.uriSnapshot = uriSnapshot
    }

    def getUriRelease() {
        return uriRelease
    }

    void setUriRelease(uriRelease) {
        this.uriRelease = uriRelease
    }

    def getName() {
        return name
    }

    void setName(name) {
        this.name = name
    }

    def getUsername() {
        return username
    }

    void setUsername(username) {
        this.username = username
    }

    def getPassword() {
        return password
    }

    void setPassword(password) {
        this.password = password
    }
}