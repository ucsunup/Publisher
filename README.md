Publisher
========

You can visit [The website](https://github.com/ucsunup/Publisher)

### Use
--------------------

Publisher is a util for publish artifact with Android and support: maven, jcenter, bintray

1. First config in `build.gradle`
```groovy
buildscript {
	repositories {
		mavenCentral()
	}

	dependencies {
		classpath 'com.ucsunup:publisher:(insert latest version)'
	}
}
apply plugin: 'com.ucsunup.publisher'
```
Latest version is: `0.0.1`

2. Then you should config in `local.properties`
```xml
#################### Publication ####################
# Group Id
pom.groupId=
# Artifact Id
pom.artifactId=
# Version
pom.version=
# Publication Name
pom.name=
# Pom Description
pom.description=
# Pom Url
pom.url=
# Pom Licenses Info
pom.licenses.license.name=
pom.licenses.license.url=
pom.licenses.license.distribution=
# Pom Developers Info
pom.developers.developer.id=
pom.developers.developer.name=
pom.developers.developer.email=
# Pom Scm Info
pom.scm.connection=
pom.scm.developerConnection=
pom.scm.url=
#################### Sign ####################
signing.keyId=
signing.password=
signing.secretKeyRingFile=
```

3. If push maven configed by `local.properties`
```xml
gradlew publish
```
if push local maven
```xml
gradlew publishToMavenLocal
```
if want to check more tasks and check `Publisher tasks`
```xml
gradlew tasks
```

For more details see [Publisher](https://github.com/ucsunup/Publisher).

### QA
--------------------

If you have any question, please write it.

### Contribute
--------------------

If you interesting in it and want to contribute, i will be glad for that.

### License
--------------------
	Copyright 2019 ucsunup

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
