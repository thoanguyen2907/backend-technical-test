# Use an official Maven image to build the app
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application with Maven
RUN mvn clean install -DskipTests

# Use an official OpenJDK image as the base image for the final container
FROM eclipse-temurin:17-jdk AS runtime

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the app will run on
EXPOSE 8080

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
