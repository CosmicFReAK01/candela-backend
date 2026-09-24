# Multi-stage Docker build for CandelaConstruction Microservices
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copy root pom and modules
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./
COPY api-gateway api-gateway
COPY project-service project-service
COPY tendering-service tendering-service
COPY operations-service operations-service
COPY hr-news-service hr-news-service

# Build all module jars
RUN mvn clean package -DskipTests

# Runtime stage with slim JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy compiled jars from build stage
COPY --from=builder /app/project-service/target/project-service-1.0.0-SNAPSHOT.jar ./jars/
COPY --from=builder /app/tendering-service/target/tendering-service-1.0.0-SNAPSHOT.jar ./jars/
COPY --from=builder /app/operations-service/target/operations-service-1.0.0-SNAPSHOT.jar ./jars/
COPY --from=builder /app/hr-news-service/target/hr-news-service-1.0.0-SNAPSHOT.jar ./jars/
COPY --from=builder /app/api-gateway/target/api-gateway-1.0.0-SNAPSHOT.jar ./jars/

COPY render-entrypoint.sh ./
RUN chmod +x render-entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/app/render-entrypoint.sh"]
