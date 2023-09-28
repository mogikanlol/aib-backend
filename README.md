## Requirements
* Java 20
* Gradle 8.3

## How to run
### Build
```console
gradlew.bat clean build
```
### Run
```console
gradlew.bat :eureka-discovery:bootRun
```
```console
gradlew.bat :zuul-gateway:bootRun
```
```console
gradlew.bat :aib-data:bootRun --args='--spring.profiles.active=local'
```