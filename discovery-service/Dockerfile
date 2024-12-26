# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

# Expose the application port
EXPOSE 8761

# Run the jar file
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]