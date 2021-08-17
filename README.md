# Jexter

![Build Status](https://github.com/thundra-io/jexter/actions/workflows/build.yml/badge.svg)
[![License](https://img.shields.io/github/license/thundra-io/jexter.svg)](http://www.apache.org/licenses/LICENSE-2.0)
![Lifecycle](https://img.shields.io/osslifecycle/thundra-io/jexter.svg)

Extensions/Plugins for JVM test frameworks (JUnit 4, JUnit 5, ...)

## Get Jexter

Binaries are available from [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cio.thundra).

|Group|Artifact|Latest Stable Version|
|-----------|---------------|---------------------|
|io.thundra|jexter-*|[![Maven Central](https://img.shields.io/maven-central/v/io.thundra/jexter.svg)]()|

For Maven:

```xml
<dependency>
	<groupId>io.thundra</groupId>
	<artifactId>jexter-...</artifactId>
	<version><!--...--></version>
	<scope>test</scope>
</dependency>
```

For Gradle:

```groovy
testCompile group: 'io.thundra', name: 'jexter-...', version: /*...*/
```

Below are the various artifacts published:

|Artifact|Description|
|-----------|---------------|
|[jexter-core](jexter-core)|Core module|
|[jexter-junit4-core](jexter-junit4-core)|JUnit4 core module|
|[jexter-junit5-core](jexter-junit5-core)|JUnit5 core module|

## Versioning

Artifact versions are in `X.Y.Z` format
- `X`: Major version number. 
  - Increases only when there are big API and/or architectural changes. 
  - Breaks backward compatibility.
- `Y`: Minor version number. 
  - Increases for small API changes and big improvements. 
  - Breaks backward compatibility.
- `Z`: Patch version number. 
  - Increases for bug fixes and small improvements. 
  - Doesn’t break backward compatibility. 

## Requirements

* JDK 1.8+ (for build and execution)
* Maven 3.x (for build)

## Build

To build:

```
$ git clone git@github.com:thundra-io/jexter.git
$ cd jexter/
$ mvn clean install
```

## How to Use

### Utilities/Helpers

#### **`EnvironmentVariableHelper`**

##### Set environment variable
```java
import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;

...

EnvironmentVariableHelper.set("env-var-name", "env-var-value");

```

##### Remove environment variable
```java
import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;

...

EnvironmentVariableHelper.remove("env-var-name");

```

### JUnit 4 Rules

#### **`EnvironmentVariableSandboxRule`**

Stores environment variables before the test and restores them back to original value after the test
 
- Add `jexter-junit4-core` dependency
- Define EnvironmentVariableSandboxRule` as test or class level rule 
**Method level:**
```java
import io.thundra.jexter.junit4.core.envvar.EnvironmentVariableSandboxRule;

...

public class TheTest {

    ...

    @Rule
    public EnvironmentVariableSandboxRule rule = new EnvironmentVariableSandboxRule();

    ...

}
```
**Class level:**
```java
import io.thundra.jexter.junit4.core.envvar.EnvironmentVariableSandboxRule;

...

public class TheTest {

    ...

    @ClassRule
    public static EnvironmentVariableSandboxRule rule = new EnvironmentVariableSandboxRule();

    ...

}
```

### JUnit 5 Extension

#### **`EnvironmentVariableSandboxExtension`**

- Add `jexter-junit5-core` dependency
- Annotate test method or class by `@EnvironmentVariableSandbox` annotation

**Method level:**
```java
import io.thundra.jexter.junit5.core.envvar.EnvironmentVariableSandbox;

...

public class TheTest {

    ...

    @EnvironmentVariableSandbox
    @Test
    public void test() {
        ...
    }

    ...

}
```
**Class level:**
```java
import io.thundra.jexter.junit5.core.envvar.EnvironmentVariableSandbox;

...

@EnvironmentVariableSandbox
public class TheTest {

    ...

    @Test
    public void test() {
        ...
    }

    ...

}
```

## Issues and Feedback

[![Issues](https://img.shields.io/github/issues/thundra-io/jexter.svg)](https://github.com/thundra-io/jexter/issues?q=is%3Aopen+is%3Aissue)
[![Closed issues](https://img.shields.io/github/issues-closed/thundra-io/jexter.svg)](https://github.com/thundra-io/jexter/issues?q=is%3Aissue+is%3Aclosed)

Please use [GitHub Issues](https://github.com/thundra-io/jexter/issues) for any bug report, feature request and support.

## Contribution

[![Pull requests](https://img.shields.io/github/issues-pr/thundra-io/jexter.svg)](https://github.com/thundra-io/jexter/pulls?q=is%3Aopen+is%3Apr)
[![Closed pull requests](https://img.shields.io/github/issues-pr-closed/thundra-io/jexter.svg)](https://github.com/thundra-io/jexter/pulls?q=is%3Apr+is%3Aclosed)
[![Contributors](https://img.shields.io/github/contributors/thundra-io/jexter.svg)]()

If you would like to contribute, please 
- Fork the repository on GitHub and clone your fork.
- Create a branch for your changes and make your changes on it.
- Send a pull request by explaining clearly what is your contribution.

> Tip: Please check the existing pull requests for similar contributions and consider submit an issue to discuss the proposed feature before writing code.

## LICENSE

Copyright (c) 2018 Thundra, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
