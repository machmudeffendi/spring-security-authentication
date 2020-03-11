# Spring Security Authentication
This is an example Spring Authentication with a Monolith-based.

## Getting Started
### Installing
Before running application, you should install the npm dependencies
in this path: /resources/static/plugin/
```$xslt
> npm install -f
```
### Build
You can build application with this command :

```$xslt
> mvn clean
> mvn package install
```
and running build result with this command: 
```$xslt
> java -Jar target/mywebserviceapp-0.0.1-SNAPSHOT.jar
```
### Running
You can also running this application without building application first:
```$xslt
> mvn spring-boot:run
```