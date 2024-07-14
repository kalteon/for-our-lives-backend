# Use the official Gradle image to build the project
FROM gradle:7.2-jdk17 AS build
WORKDIR /server
COPY . .
RUN gradle build -x test

# Use a smaller JRE image to run the project
FROM openjdk:17-jdk-slim
WORKDIR /server
COPY --from=build /server/build/libs/*.jar diary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "diary.jar"]
