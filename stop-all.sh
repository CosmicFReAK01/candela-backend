#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR"

echo "Stopping all CandelaConstruction Spring Boot Microservices..."

for name in api-gateway project-service tendering-service operations-service hr-news-service; do
  pid_file="logs/$name.pid"
  if [ -f "$pid_file" ]; then
    pid=$(cat "$pid_file")
    if ps -p "$pid" > /dev/null 2>&1; then
      echo "Killing $name (PID $pid)..."
      kill "$pid" || true
    fi
    rm -f "$pid_file"
  fi
done

# Secondary cleanup by port
for port in 8080 8081 8082 8083 8084; do
  pids=$(lsof -ti :$port || true)
  if [ -n "$pids" ]; then
    echo "Killing remaining process on port $port ($pids)..."
    kill -9 $pids 2>/dev/null || true
  fi
done

echo "All services stopped."
