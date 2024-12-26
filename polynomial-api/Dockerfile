FROM openjdk:17-slim
WORKDIR /app
COPY target/polynomial-service-0.0.1-SNAPSHOT.jar /app/polynomial-api.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "polynomial-api.jar"]