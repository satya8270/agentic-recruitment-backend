# Step 1: Build the app using Maven with Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run the compiled JAR with Java 21 JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Dserver.port=8081", "-jar", "app.jar"]