FROM openjdk:17-slim
WORKDIR /app
COPY target/roots-service-0.0.1-SNAPSHOT.jar /app/roots-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "roots-service.jar"]