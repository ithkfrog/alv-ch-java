alv-ch-java [![Build Status](https://travis-ci.org/alv-ch/alv-ch-java.svg?branch=master)](https://travis-ci.org/alv-ch/alv-ch-java)
===========

Contains components for various fundamental Java concerns, including Java implementations of WebAPIs. Please be aware that all components are in a conceptual state and may be changed or even removed.

Published Artifacts
-------------------

alv-ch-java artificats are available from http://alvch.artifactoryonline.com.

Example of Snippet to add to your Maven `settings.xml` (or `pom.xml`):

```xml
  <profiles>

    <profile>
      <id>alv-ch</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>alvch-libs-releases</id>
          <name>ALV Switzerland Releases</name>
          <url>http://alvch.artifactoryonline.com/alvch/libs-releases</url>
        </repository>
        <repository>
          <snapshots />
          <id>alvch-libs-snapshots</id>
          <name>ALV Swizterland Snapshots</name>
          <url>http://alvch.artifactoryonline.com/alvch/libs-snapshots</url>
        </repository>
      </repositories>
    </profile>

  </profiles>
```
