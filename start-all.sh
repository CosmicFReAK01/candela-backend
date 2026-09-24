#!/usr/bin/env bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR"

JAVA_BIN="java"
if [ -d "/opt/homebrew/Cellar/openjdk@17" ]; then
  OPENJDK17_HOME=$(find /opt/homebrew/Cellar/openjdk@17 -name "Home" -type d | head -n 1)
  if [ -n "$OPENJDK17_HOME" ]; then
    export JAVA_HOME="$OPENJDK17_HOME"
    JAVA_BIN="$JAVA_HOME/bin/java"
  fi
fi

echo "==========================================================="
echo " Starting CandelaConstruction Spring Boot Microservices"
echo " Using Java: $JAVA_BIN"
echo "==========================================================="

mkdir -p logs

start_service() {
  local name=$1
  local port=$2
  local jar="$name/target/$name-1.0.0-SNAPSHOT.jar"
  
  if [ ! -f "$jar" ]; then
    echo "[$name] JAR not found! Compiling..."
    ./mvnw package -DskipTests=true -pl "$name"
  fi

  echo "Starting $name on port $port..."
  nohup "$JAVA_BIN" -jar "$jar" > "logs/$name.log" 2>&1 &
  echo $! > "logs/$name.pid"
  echo "[$name] PID $(cat logs/$name.pid)"
}

start_service "project-service" 8081
start_service "tendering-service" 8082
start_service "operations-service" 8083
start_service "hr-news-service" 8084

# Wait a few seconds before launching gateway
echo "Waiting 4s for core microservices to initialize..."
sleep 4

start_service "api-gateway" 8080

echo "==========================================================="
echo " All services started!"
echo " - API Gateway:        http://localhost:8080"
echo " - Project Service:    http://localhost:8081"
echo " - Tendering Service:  http://localhost:8082"
echo " - Operations Service: http://localhost:8083"
echo " - HR & News Service:  http://localhost:8084"
echo " Logs are saved to $DIR/logs/"
echo " Run ./stop-all.sh to terminate all background services."
echo "==========================================================="
