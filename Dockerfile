# Use a Java runtime as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built jar from target folder into container
COPY target/online-api-book-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the jar
ENTRYPOINT ["java","-jar","app.jar"]