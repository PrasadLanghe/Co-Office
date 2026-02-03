# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory inside the container
WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage cache
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies offline
RUN ./mvnw dependency:go-offline -B

# Copy all source code
COPY src src

# Package the application
RUN ./mvnw package -DskipTests

# Expose port (should match your application.properties server.port)
EXPOSE 8181

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","target/Co-Office-0.0.1-SNAPSHOT.jar"]
