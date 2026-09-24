# Multi-stage Docker build for CandelaConstruction Microservices
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copy root pom and modules
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./
COPY project-service project-service
COPY tendering-service tendering-service
COPY operations-service operations-service
COPY hr-news-service hr-news-service
COPY api-gateway api-gateway

# Build all module jars
RUN mvn clean package -DskipTests

# Runtime stage with slim JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy unified cluster jar
COPY --from=builder /app/api-gateway/target/api-gateway-1.0.0-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# Run single optimized JVM with 256MB max heap (fits well within 512MB RAM tier)
CMD ["java", "-Xmx256m", "-Xms64m", "-jar", "app.jar"]
