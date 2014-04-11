alv-ch-java [![Build Status](https://travis-ci.org/alv-ch/alv-ch-java.svg?branch=master)](https://travis-ci.org/alv-ch/alv-ch-java)
===========

Contains components for various fundamental Java concerns, including Java implementations of WebAPIs. Please be aware that all components are in a conceptual state and may be changed or even removed.

Published Artifacts
-------------------

alv-ch-java artificats are available from http://alvch.artifactoryonline.com.

Example of Snippet to add to your Maven `settings.xml`:

```xml
  <profiles>

    <profile>
      <id>alv-ch</id>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>libs-releases</name>
          <url>http://alvch.artifactoryonline.com/alvch/libs-releases</url>
        </repository>
        <repository>
          <snapshots />
          <id>snapshots</id>
          <name>libs-snapshot-repos</name>
          <url>http://alvch.artifactoryonline.com/alvch/libs-snapshots</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>plugins-releases</name>
          <url>http://alvch.artifactoryonline.com/alvch/plugins-releases</url>
        </pluginRepository>
        <pluginRepository>
          <snapshots />
          <id>snapshots</id>
          <name>plugins-snapshots</name>
          <url>http://alvch.artifactoryonline.com/alvch/plugins-snapshots</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>

  </profiles>

  <activeProfiles>
    <activeProfile>alv-ch</activeProfile>
  </activeProfiles>

```

For more advanced Maven configuration, or integration with Gradle or Ivy, you can use [online Artifactory Settings Generators](http://alvch.artifactoryonline.com/alvch/webapp/mavensettings.html).
