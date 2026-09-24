# CandelaConstruction — Spring Boot Microservices Ecosystem

A multi-module Spring Boot 3.3.3 + Java 17 enterprise microservices backend built for the **CandelaConstruction** EPC Pipeline Transmission & Infrastructure platform (`pipeline-construction-site`).

---

## 🏗 System Architecture

```
                                  +---------------------------+
                                  |   Next.js 15.5 Frontend   |
                                  | (Port 3000 / Browser SPA) |
                                  +-------------+-------------+
                                                |
                                                | HTTP / REST
                                                v
                              +-----------------------------------+
                              |     API Gateway (Spring Boot)     |
                              |             Port 8080             |
                              +--+--------+----------+---------+--+
                                 |        |          |         |
                  +--------------+        |          |         +---------------+
                  |                       |          |                         |
                  v                       v          v                         v
        +-------------------+   +-------------+   +------------+   +-------------------+
        |  project-service  |   |  tendering- |   | operations-|   |  hr-news-service  |
        |     (Port 8081)   |   |   service   |   |  service   |   |     (Port 8084)   |
        |                   |   | (Port 8082) |   | (Port 8083)|   |                   |
        +-------------------+   +-------------+   +------------+   +-------------------+
        | • Projects        |   | • RFQ Engine|   | • SCADA    |   | • Career Openings |
        | • Services        |   | • ASME B31.8|   |   Telemetry|   | • Job Applications|
        | • Heavy Fleet     |   |   Estimator |   | • ESD Trip |   | • Press Releases  |
        | • Stats & States  |   | • Tonnage   |   | • HSE Stats|   | • Media Room      |
        +-------------------+   +-------------+   +------------+   +-------------------+
```

---

## 🧩 Microservices Directory & Port Mappings

| Service | Port | Description | Primary Endpoints |
|---|---|---|---|
| **`api-gateway`** | **8080** | Central ingress router, reverse proxy, global CORS handling & unified health checks | `/api/gateway/health`, `/api/gateway/info` |
| **`project-service`** | **8081** | Portfolio of pipeline EPC projects, core capabilities, fleet machinery, and state footprints | `/api/projects`, `/api/services`, `/api/fleet`, `/api/stats`, `/api/states` |
| **`tendering-service`** | **8082** | RFQ submission pipeline with automated reference numbers (`RFQ-2026-XXXX`) & engineering cost/tonnage estimator | `POST /api/rfq`, `POST /api/calculator/estimate` |
| **`operations-service`** | **8083** | Real-time SCADA telemetry engine (SV-01 to SV-04), live jitter, emergency ESD trip & valve reset, and HSE metrics | `GET /api/scada/telemetry`, `POST /api/scada/trip`, `POST /api/scada/reset`, `GET /api/scada/logs`, `GET /api/hse` |
| **`hr-news-service`** | **8084** | Talent acquisition, candidate applications (`APP-2026-XXXX`), and corporate press releases | `GET /api/careers`, `POST /api/careers/apply`, `GET /api/news` |

---

## 🚀 Quick Start Instructions

### Prerequisites
- **Java 17+** (e.g. OpenJDK 17)
- **Maven 3.9+** (or use included `./mvnw`)
- **Node.js 18+** (for frontend)

### 1. Build All Services
```bash
cd /Users/arjunyadav/Downloads/pipeline-construction-Microservices
./mvnw clean package -DskipTests
```

### 2. Start All Microservices
Run the automated background launcher script:
```bash
./start-all.sh
```
This boots all 5 services sequentially with health polling and logs each service output into `./logs/<service-name>.log`.

Verify all services are healthy:
```bash
curl http://localhost:8080/api/gateway/health
```
Expected output:
```json
{
  "api-gateway": "UP",
  "project-service": "UP",
  "tendering-service": "UP",
  "operations-service": "UP",
  "hr-news-service": "UP"
}
```

### 3. Stop All Microservices
```bash
./stop-all.sh
```

### 4. Docker Compose (Alternative)
```bash
docker-compose up --build -d
```

---

## 📡 REST API Specifications

### 1. Tendering & Estimator API (`tendering-service`)

#### Submit Request for Quotation (RFQ)
`POST http://localhost:8080/api/rfq`
```json
{
  "companyName": "GAIL India Limited",
  "contactPerson": "Rajesh Sharma",
  "email": "rsharma@gail.co.in",
  "phone": "+91 98765 43210",
  "location": "Gujarat to Rajasthan",
  "approxLength": "320",
  "diameter": "36\"",
  "projectType": "CROSS_COUNTRY_GAS",
  "description": "Natural gas transmission trunkline."
}
```
**Response (201 Created):**
```json
{
  "success": true,
  "referenceNo": "RFQ-2026-7935",
  "message": "Request for Quotation successfully registered with CandelaConstruction Tendering Division.",
  "status": "PENDING_REVIEW",
  "estimatedTurnaround": "48 Hours Commercial Review",
  "timestamp": "2026-09-17T21:11:18.854459"
}
```

#### ASME B31.8 / API 5L Pipeline Calculator
`POST http://localhost:8080/api/calculator/estimate`
```json
{
  "diameterInches": 36,
  "wallThicknessMm": 15.9,
  "lengthKm": 120,
  "terrainIndex": 1,
  "pressureIndex": 2
}
```
**Response (200 OK):**
```json
{
  "steelTonnage": "42,278",
  "waterKL": "84,430",
  "joints": "9,917",
  "costCr": "2297.1",
  "grade": "API 5L X70 / X80 PSL2",
  "method": "Controlled blasting or mechanical rock cutting",
  "standard": "API 1104 / ASME B31.8 / OISD 226",
  "lengthKm": 120.0,
  "diameterInches": 36.0
}
```

---

### 2. SCADA Operations & HSE API (`operations-service`)

#### Get Live Telemetry
`GET http://localhost:8080/api/scada/telemetry`
```json
{
  "stations": [
    { "id": "SV-01", "name": "Origin Compressor Station", "kp": "KP 0.0", "basePressure": 98.2, "pressure": 98.5, "status": "ONLINE" },
    { "id": "SV-02", "name": "Intermediate Block Valve #1", "kp": "KP 142.5", "basePressure": 94.6, "pressure": 94.5, "status": "ONLINE" },
    { "id": "SV-03", "name": "River Crossing SV Station", "kp": "KP 286.0", "basePressure": 91.3, "pressure": 91.4, "status": "ONLINE" },
    { "id": "SV-04", "name": "Terminus Delivery Station", "kp": "KP 422.8", "basePressure": 87.8, "pressure": 88.0, "status": "ONLINE" }
  ],
  "flowRate": 79.4,
  "esdActive": false,
  "activeAlarmCount": 0,
  "gridStatus": "NOMINAL OPERATING PRESSURE"
}
```

#### Trigger Emergency ESD Trip
`POST http://localhost:8080/api/scada/trip?stationId=SV-04`
```json
{
  "success": true,
  "stationId": "SV-04",
  "status": "TRIPPED",
  "message": "ESD Emergency trip command successfully executed on station SV-04"
}
```

#### Reset Sectionalizing Valve
`POST http://localhost:8080/api/scada/reset?stationId=SV-04`
```json
{
  "success": true,
  "stationId": "SV-04",
  "status": "ONLINE",
  "message": "Station SV-04 re-pressurized and restored to ONLINE"
}
```

#### Get SCADA Audit Logs
`GET http://localhost:8080/api/scada/logs`

#### Get HSE Safety Stats
`GET http://localhost:8080/api/hse`

---

### 3. Projects & Capabilities API (`project-service`)

- `GET http://localhost:8080/api/projects` (Supports filter `?category=gas|crude|hdd|cgd`)
- `GET http://localhost:8080/api/projects/{slug}`
- `GET http://localhost:8080/api/services`
- `GET http://localhost:8080/api/fleet`
- `GET http://localhost:8080/api/stats`
- `GET http://localhost:8080/api/states`

---

### 4. HR & Corporate News API (`hr-news-service`)

- `GET http://localhost:8080/api/careers`
- `POST http://localhost:8080/api/careers/apply`
- `GET http://localhost:8080/api/news`

---

## 💻 Frontend Integration Details

The Next.js frontend in `../pipeline-construction-site` integrates cleanly with this ecosystem via:
1. **API Client Layer**: `lib/api.ts` connecting directly to `http://localhost:8080`.
2. **Interactive Components**:
   - `components/RFQForm.tsx` (Submits commercial tender quotes with real-time feedback).
   - `components/PipelineCalculator.tsx` (Connects to backend ASME B31.8 estimation engine).
   - `components/ScadaSimulator.tsx` (Polls telemetry, triggers ESD trip commands, views logs).
   - `components/CareersSection.tsx` (Browses job openings and submits applications).
3. **Resilient Offline Fallback**: All components feature graceful fallback handling so the site remains fully operational even if backend services are offline.
