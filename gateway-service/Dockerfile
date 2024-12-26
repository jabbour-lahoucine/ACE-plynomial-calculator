FROM openjdk:17-slim
WORKDIR /app
COPY target/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]