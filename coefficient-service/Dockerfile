FROM openjdk:17-slim
WORKDIR /app
COPY target/coefficient-service-0.0.1-SNAPSHOT.jar /app/coefficient-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "coefficient-service.jar"]