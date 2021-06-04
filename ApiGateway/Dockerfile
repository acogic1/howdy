FROM openjdk:12.0.1
EXPOSE 8090
ADD target/apigateway-jar.jar apigateway-jar.jar
ENTRYPOINT ["java", "-jar", "/apigateway-jar.jar"]