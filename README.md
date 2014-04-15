alv-ch-java [![Build Status](https://travis-ci.org/alv-ch/alv-ch-java.svg?branch=master)](https://travis-ci.org/alv-ch/alv-ch-java)
===========

Contains components for various fundamental Java concerns, including Java implementations of WebAPIs. Please be aware that all components are in a conceptual state and may be changed or even removed.

Maven Configuration
-------------------

alv-ch-java final and snapshot releases are available from http://alvch.artifactoryonline.com.

A dependency on a final release might look like:

```xml
        <dependency>
            <groupId>ch.alv.components</groupId>
            <artifactId>components-webapi</artifactId>
            <version>1.0.0</version>
        </dependency>

        <repository>
            <id>alvch-libs-releases</id>
            <name>ALV Switzerland Releases</name>
            <url>http://alvch.artifactoryonline.com/alvch/libs-releases</url>
        </repository>
```

A dependency on a snapshot release might look like:

```xml
        <dependency>
            <groupId>ch.alv.components</groupId>
            <artifactId>components-iam</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>

        <repository>
          <id>alvch-libs-snapshots</id>
          <name>ALV Switzerland Snapshots</name>
          <url>http://alvch.artifactoryonline.com/alvch/libs-snapshots</url>
        </repository>
```
