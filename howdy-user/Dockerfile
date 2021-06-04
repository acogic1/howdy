FROM openjdk:12.0.1
EXPOSE 8080
ADD target/user-jar.jar user-jar.jar
ENTRYPOINT ["java", "-jar", "/user-jar.jar"]