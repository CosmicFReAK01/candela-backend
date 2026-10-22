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

# Copy the Spring Boot repackaged executable JAR
COPY --from=builder /app/api-gateway/target/api-gateway-1.0.0-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# Run single optimized JVM — PORT, DB_URL, DB_USERNAME, DB_PASSWORD come from Render env vars
CMD ["sh", "-c", "exec java -Xmx256m -Xms64m -Dserver.port=${PORT:-8080} -Dspring.jpa.hibernate.ddl-auto=${DDL_AUTO:-validate} -jar app.jar"]
