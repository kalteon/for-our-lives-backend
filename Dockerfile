# Use the official Gradle image to build the project
FROM gradle:7.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# Use a smaller JRE image to run the project
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
