# Step 1: Build stage (use Gradle)
FROM gradle:8.14.3-jdk-21-and-24 AS build
WORKDIR /app
LABEL authors="Raag"

# Copy Gradle files first (to leverage caching)
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the application
RUN gradle clean build -x test

# Step 2: Runtime stage (slim JDK)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy JAR from Gradle build output
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]