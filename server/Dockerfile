# Stage 1: Build the application
FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Stage 2: Build the runtime image
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/superflix-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "superflix-0.0.1-SNAPSHOT.jar"]
