FROM maven:3.6.3 AS maven
LABEL MAINTAINER="biniamasnake@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=money-transfer-api.jar
WORKDIR /opt/app
# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","money-transfer-api.jar"]
EXPOSE 8080

#FROM adoptopenjdk:11-jre-hotspot
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","/app.jar"]
#EXPOSE 8080
