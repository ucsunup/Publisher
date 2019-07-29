package com.ucsunup.publisher.core

@Singleton
class SignProperties {
    /**
     * Key Id
     */
    def KEY_SIGNING_KEY_ID = "signing.keyId"
    /**
     * Key password
     */
    def KEY_SIGNING_PASSWORD = "signing.password"
    /**
     * Key private key file
     */
    def KEY_SIGNING_SECRET_KEY_RING_FILE = "signing.secretKeyRingFile"

    def keyId
    def password
    def secretKeyRingFile

    /**
     * Check current if need to sign
     */
    def needSign = false

    def init(Properties properties) {
        if (properties.containsKey(KEY_SIGNING_KEY_ID)) {
            setKeyId(properties.getProperty(KEY_SIGNING_KEY_ID))
        }
        if (properties.containsKey(KEY_SIGNING_PASSWORD)) {
            setPassword(properties.getProperty(KEY_SIGNING_PASSWORD))
        }
        if (properties.containsKey(KEY_SIGNING_SECRET_KEY_RING_FILE)) {
            setSecretKeyRingFile(properties.getProperty(KEY_SIGNING_SECRET_KEY_RING_FILE))
        }
    }

    def getKeyId() {
        return keyId
    }

    void setKeyId(keyId) {
        this.keyId = keyId
        if (keyId != null) {
            setNeedSign(true)
        }
    }

    def getPassword() {
        return password
    }

    void setPassword(password) {
        this.password = password
    }

    def getSecretKeyRingFile() {
        return secretKeyRingFile
    }

    void setSecretKeyRingFile(secretKeyRingFile) {
        this.secretKeyRingFile = secretKeyRingFile
    }

    def getNeedSign() {
        return needSign
    }

    void setNeedSign(needSign) {
        this.needSign = needSign
    }
}