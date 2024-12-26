FROM openjdk:17-slim
WORKDIR /app
COPY target/factorization-service-0.0.1-SNAPSHOT.jar /app/factorization-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "factorization-service.jar"]