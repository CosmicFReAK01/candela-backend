#!/usr/bin/env sh
set -e

echo "=========================================================="
echo " Starting CandelaConstruction Cloud Microservices Cluster"
echo " Render Assigned Port: ${PORT:-8080}"
echo " Database Target: ${DB_URL:-jdbc:postgresql://localhost:5433/GasPipeline}"
echo "=========================================================="

mkdir -p /app/logs

# Start domain microservices in background with controlled JVM memory limits for 512MB RAM tier
java -Xmx110m -Xms48m -jar /app/jars/project-service-1.0.0-SNAPSHOT.jar > /app/logs/project-service.log 2>&1 &
java -Xmx110m -Xms48m -jar /app/jars/tendering-service-1.0.0-SNAPSHOT.jar > /app/logs/tendering-service.log 2>&1 &
java -Xmx110m -Xms48m -jar /app/jars/operations-service-1.0.0-SNAPSHOT.jar > /app/logs/operations-service.log 2>&1 &
java -Xmx110m -Xms48m -jar /app/jars/hr-news-service-1.0.0-SNAPSHOT.jar > /app/logs/hr-news-service.log 2>&1 &

echo "Waiting for domain microservices to bind..."
sleep 4

# Launch API Gateway in foreground as the main process
exec java -Xmx110m -Xms48m -Dserver.port=${PORT:-8080} -jar /app/jars/api-gateway-1.0.0-SNAPSHOT.jar
