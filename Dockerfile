# Stage 1: Build with Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# First copy only the POM file to leverage Docker cache
COPY pom.xml .

# Download dependencies (this layer will be cached unless POM changes)
RUN mvn dependency:go-offline

# Copy source files
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:21-jre-alpine

# Set labels for better maintainability
LABEL maintainer="your-email@example.com"
LABEL version="0.0.1-SNAPSHOT"
LABEL description="Demo project for Spring Boot with Swagger"

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

WORKDIR /app

# Copy the built JAR file from the builder stage
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar

# Use ENTRYPOINT with exec form and add JVM options
ENTRYPOINT ["java", "-jar", "/app/app.jar"]