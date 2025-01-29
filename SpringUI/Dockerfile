# Étape de construction
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Étape finale
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/src/main/webapp /app/src/main/webapp 
ENTRYPOINT ["java", "-jar", "app.jar"]
