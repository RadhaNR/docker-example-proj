FROM openjdk:latest

COPY target/*.jar bk-users.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bk-users.jar"]