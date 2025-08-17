# Godmansbok

A backend system for **godman** (legal guardianship in Sweden).  
It helps manage clients, their finances, and assets with authentication and reporting features.  

## Features  
- 🔑 User registration, login & logout (JWT auth)  
- 👤 Manage clients (CRUD)  
- 💰 Manage accounts & transactions  
- 📑 Track assets & liabilities  
- 📊 Generate and export yearly summaries per client in PDF format  

## Tech Stack 

### Backend
- Java
- Spring Boot   
- PostgreSQL

### Frontend
- React
- TypeScript  

## Frontend

### Clone and run it:
```bash
git clone https://github.com/ludwig-dev/godmansbok-fe.git
cd godmansbok-fe
npm install
npm run dev
```

Repo: [godmansbok-fe](https://github.com/ludwig-dev/godmansbok-fe)

## Backend

Clone and run the backend:
```bash
git clone https://github.com/ludwig-dev/godmansbok.git
cd godmansbok
```

### Environment Variables
Create a `.env` file in the project root:
```env
DB_URL=jdbc:postgresql://localhost:5432/yourdbname
DB_USER=yourdbuser
DB_PASSWORD=yourdbpassword
JWT_SECRET=yourjwtsecret
```

### Run the app
```bash
./mvnw spring-boot:run
```


API base URL: `http://localhost:8080`  
Repo: [godmansbok](https://github.com/ludwig-dev/godmansbok)

## Endpoints Overview

| Category | Endpoints |
| --- | --- |
| **Auth** | `/api/auth/register`, `/api/auth/login`, `/api/auth/logout` |
| **Clients** | `/api/clients`, `/api/clients/{clientId}` |
| **Accounts** | `/api/clients/{clientId}/accounts`, `/api/clients/{clientId}/accounts/{accountId}` |
| **Transactions** | `/api/clients/{clientId}/accounts/{accountId}/transactions`, `/api/clients/{clientId}/accounts/{accountId}/transactions/{transactionId}` |
| **Other Assets** | `/api/clients/{clientId}/other-assets`, `/api/clients/{clientId}/other-assets/{otherAssetId}` |
| **Liabilities** | `/api/clients/{clientId}/liabilities`, `/api/clients/{clientId}/liabilities/{liabilityId}` |
| **Summary** | `/api/clients/{clientId}/summary` |
