# Start with base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information about who maintains the image
MAINTAINER eazybytes.com

# Add the application's JAR to the image
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]
