# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

# Stage 2: Build the runtime image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/superflix-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "superflix-0.0.1-SNAPSHOT.jar"]
