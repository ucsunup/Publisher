package com.ucsunup.publisher.core

@Singleton
class MavenPublicationProperties {
    /**
     * Config: group id
     */
    def KEY_GROUP_ID = "pom.groupId"
    /**
     * Config: artifact id
     */
    def KEY_ARTIFACT_ID = "pom.artifactId"
    /**
     * Config: publication version
     */
    def KEY_VERSION = "pom.version"
    /**
     * Config: pom name
     */
    def KEY_POM_NAME = "pom.name"
    /**
     * Config: pom description
     */
    def KEY_POM_DESCRIPTION = "pom.description"
    /**
     * Config: pom uri
     */
    def KEY_POM_URL = "pom.url"
    /**
     * Config: pom license name
     */
    def KEY_POM_LICENSES_LICENSE_NAME = "pom.licenses.license.name"
    /**
     * Config: pom license url
     */
    def KEY_POM_LICENSES_LICENSE_URL = "pom.licenses.license.url"
    /**
     * Config: pom license distribution
     */
    def KEY_POM_LICENSES_LICENSE_DISTRIBUTION = "pom.licenses.license.distribution"
    /**
     * Config: pom developer id
     */
    def KEY_POM_DEVELOPERS_DEVELOPER_ID = "pom.developers.developer.id"
    /**
     * Config: pom developer name
     */
    def KEY_POM_DEVELOPERS_DEVELOPER_NAME = "pom.developers.developer.name"
    /**
     * Config: pom developer email
     */
    def KEY_POM_DEVELOPERS_DEVELOPER_EMAIL = "pom.developers.developer.email"
    /**
     * Config: pom scm connection
     */
    def KEY_POM_SCM_CONNECTION = "pom.scm.connection"
    /**
     * Config: pom scm developer connection
     */
    def KEY_POM_SCM_DEVELOPER_CONNECTION = "pom.scm.developerConnection"
    /**
     * Config: pom scm url
     */
    def KEY_POM_SCM_URL = "pom.scm.url"

    private def groupId
    private def artifactId
    private def version

    private def pomName
    private def pomDescription
    private def pomUrl
    private def pomLicensesLicenseName
    private def pomLicensesLicenseUrl
    private def pomLicensesLicenseDistribution

    private def pomDevelopersDeveloperId
    private def pomDevelopersDeveloperName
    private def pomDevelopersDeveloperEmail

    private def pomScmConnection
    private def pomScmDeveloperConnection
    private def pomScmUrl

    def init(Properties properties) {
        if (properties.containsKey(KEY_GROUP_ID)) {
            setGroupId(properties.getProperty(KEY_GROUP_ID))
        }
        if (properties.containsKey(KEY_ARTIFACT_ID)) {
            setArtifactId(properties.getProperty(KEY_ARTIFACT_ID))
        }
        if (properties.containsKey(KEY_VERSION)) {
            setVersion(properties.getProperty(KEY_VERSION))
        }
        if (properties.containsKey(KEY_POM_NAME)) {
            setPomName(properties.getProperty(KEY_POM_NAME))
        }
        if (properties.containsKey(KEY_POM_DESCRIPTION)) {
            setPomDescription(properties.getProperty(KEY_POM_DESCRIPTION))
        }
        if (properties.containsKey(KEY_POM_URL)) {
            setPomUrl(properties.getProperty(KEY_POM_URL))
        }
        if (properties.containsKey(KEY_POM_LICENSES_LICENSE_NAME)) {
            setPomLicensesLicenseName(properties.getProperty(KEY_POM_LICENSES_LICENSE_NAME))
        }
        if (properties.containsKey(KEY_POM_LICENSES_LICENSE_URL)) {
            setPomLicensesLicenseUrl(properties.getProperty(KEY_POM_LICENSES_LICENSE_URL))
        }
        if (properties.containsKey(KEY_POM_LICENSES_LICENSE_DISTRIBUTION)) {
            setPomLicensesLicenseDistribution(properties.getProperty(KEY_POM_LICENSES_LICENSE_DISTRIBUTION))
        }
        if (properties.containsKey(KEY_POM_DEVELOPERS_DEVELOPER_ID)) {
            setPomDevelopersDeveloperId(properties.getProperty(KEY_POM_DEVELOPERS_DEVELOPER_ID))
        }
        if (properties.containsKey(KEY_POM_DEVELOPERS_DEVELOPER_NAME)) {
            setPomDevelopersDeveloperName(properties.getProperty(KEY_POM_DEVELOPERS_DEVELOPER_NAME))
        }
        if (properties.containsKey(KEY_POM_DEVELOPERS_DEVELOPER_EMAIL)) {
            setPomDevelopersDeveloperEmail(properties.getProperty(KEY_POM_DEVELOPERS_DEVELOPER_EMAIL))
        }
        if (properties.containsKey(KEY_POM_SCM_CONNECTION)) {
            setPomScmConnection(properties.getProperty(KEY_POM_SCM_CONNECTION))
        }
        if (properties.containsKey(KEY_POM_SCM_DEVELOPER_CONNECTION)) {
            setPomScmDeveloperConnection(properties.getProperty(KEY_POM_SCM_DEVELOPER_CONNECTION))
        }
        if (properties.containsKey(KEY_POM_SCM_URL)) {
            setPomScmUrl(properties.getProperty(KEY_POM_SCM_URL))
        }
    }

    def getGroupId() {
        return groupId
    }

    void setGroupId(groupId) {
        this.groupId = groupId
    }

    def getArtifactId() {
        return artifactId
    }

    void setArtifactId(artifactId) {
        this.artifactId = artifactId
    }

    def getVersion() {
        return version
    }

    void setVersion(version) {
        this.version = version
    }

    def getPomName() {
        return pomName
    }

    void setPomName(pomName) {
        this.pomName = pomName
    }

    def getPomDescription() {
        return pomDescription
    }

    void setPomDescription(pomDescription) {
        this.pomDescription = pomDescription
    }

    def getPomUrl() {
        return pomUrl
    }

    void setPomUrl(pomUrl) {
        this.pomUrl = pomUrl
    }

    def getPomLicensesLicenseName() {
        return pomLicensesLicenseName
    }

    void setPomLicensesLicenseName(pomLicensesLicenseName) {
        this.pomLicensesLicenseName = pomLicensesLicenseName
    }

    def getPomLicensesLicenseUrl() {
        return pomLicensesLicenseUrl
    }

    void setPomLicensesLicenseUrl(pomLicensesLicenseUrl) {
        this.pomLicensesLicenseUrl = pomLicensesLicenseUrl
    }

    def getPomLicensesLicenseDistribution() {
        return pomLicensesLicenseDistribution
    }

    void setPomLicensesLicenseDistribution(pomLicensesLicenseDistribution) {
        this.pomLicensesLicenseDistribution = pomLicensesLicenseDistribution
    }

    def getPomDevelopersDeveloperId() {
        return pomDevelopersDeveloperId
    }

    void setPomDevelopersDeveloperId(pomDevelopersDeveloperId) {
        this.pomDevelopersDeveloperId = pomDevelopersDeveloperId
    }

    def getPomDevelopersDeveloperName() {
        return pomDevelopersDeveloperName
    }

    void setPomDevelopersDeveloperName(pomDevelopersDeveloperName) {
        this.pomDevelopersDeveloperName = pomDevelopersDeveloperName
    }

    def getPomDevelopersDeveloperEmail() {
        return pomDevelopersDeveloperEmail
    }

    void setPomDevelopersDeveloperEmail(pomDevelopersDeveloperEmail) {
        this.pomDevelopersDeveloperEmail = pomDevelopersDeveloperEmail
    }

    def getPomScmConnection() {
        return pomScmConnection
    }

    void setPomScmConnection(pomScmConnection) {
        this.pomScmConnection = pomScmConnection
    }

    def getPomScmDeveloperConnection() {
        return pomScmDeveloperConnection
    }

    void setPomScmDeveloperConnection(pomScmDeveloperConnection) {
        this.pomScmDeveloperConnection = pomScmDeveloperConnection
    }

    def getPomScmUrl() {
        return pomScmUrl
    }

    void setPomScmUrl(pomScmUrl) {
        this.pomScmUrl = pomScmUrl
    }
}