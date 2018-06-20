# AWS MFA Authentication

## Description
This tool is used to get MFA authentication code on AWS, it can also be used for any two factor authentications.

## Setup environment
### Prerequisites

1. Install JDK 1.8.x  (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and setup JAVA_HOME environment variable.
2. Install Maven 3.5.x (http://maven.apache.org/download.cgi) and setup PATH environment variable.
3. Install Tomcat 8.x (https://tomcat.apache.org/download-80.cgi)

### Building

``` shell
git clone git@cncdgitlab.ccoe.lab.emc.com:wangs56/awsauth.git
cd awsauth
mvn clean package
```

## Configurations
### Add more login users
Update file src/main/resources/config/security.properties, add item \<UserName\>=\<Password\>, for example,

admin=changeme

### Add authentication keys
Update file src/main/resources/config/keys.properties, add item \<User\>=\<Key\>, user can be any string, for example,

test=6JOLUNVA7I4QHZ5QWMKBSU3ZVUCMAVZDBVJ5C2Y5LYBJN2LIQPTOPRLDCBOJC5NK