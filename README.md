# ⚡ EmpyreanOS — AI-Powered Enterprise Operating System

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.3-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![Vanilla JS](https://img.shields.io/badge/Vanilla_JS-Frontend-yellow?logo=javascript)
![Gemini AI](https://img.shields.io/badge/Gemini_AI-Orchestration-blueviolet)
![OpenPDF](https://img.shields.io/badge/OpenPDF-Dynamic_Docs-red)
![Architecture](https://img.shields.io/badge/Architecture-Idempotent-brightgreen)
![Status](https://img.shields.io/badge/Project-Active-success)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Contributions](https://img.shields.io/badge/Contributions-Welcome-brightgreen)

EmpyreanOS is a full-stack, enterprise-grade Business Operating System that allows organizations to:

- Manage HR, Payroll, and Assets securely  
- Execute operations autonomously via an AI Command Terminal  
- Ensure financial safety with Idempotent transaction pipelines  
- Generate dynamic PDF corporate documents instantly  

Built with a Java Spring Boot backend, Vanilla JS frontend, MySQL, VS-code, and Postman.

---

## 🚀 Features

- Autonomous AI Tool-Calling Engine (God Mode)  
- Idempotent Payroll Processing (Prevents double-billing)  
- Bulletproof Concurrency (Optimistic Locking via Hibernate)  
- Strict Role-Based Access Control (Employee, Manager, Boss)  
- Dynamic PDF Offer Letter Generation  
- Financial Runway Simulator & Global Audit Logs  

---

## 📁 Project Structure

```text
EMPYREANOS/
│
├── ems-backend/
│   ├── src/main/java/com/company/ems/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── EmsBackendApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── src/test/java/
│   ├── src/test/resources/
│   ├── target/
│   └── pom.xml
│
├── ems-frontend/
│   ├── attendance.html
│   ├── calendar.html
│   ├── chat.html
│   ├── dashboard.html
│   ├── employees.html
│   ├── godmode.html
│   ├── index.html
│   ├── leaves.html
│   ├── login.html
│   ├── payroll.html
│   ├── register.html
│   ├── reports.html
│   ├── script.js
│   ├── settings.html
│   ├── style.css
│   ├── team.html
│   └── workspace.html
│
├── .gitignore
└── README.md
```

## ⚙️ Installation

### 1. Clone the repository
```bash
git clone [https://github.com/arshdeepsarsh/EmpyreanOS.git](https://github.com/arshdeepsarsh/EmpyreanOS.git)
cd EmpyreanOS
```

### 2. Setup the Database

- Open MySQL Workbench or your command line.
- Create the empty database.
- Hibernate will auto-generate the tables on boot.

**SQL**
```bash
CREATE DATABASE ems_db;
```

### 3. Add your AI API key

- Navigate to ems-backend/src/main/resources/application.properties
- Add your Gemini API Key for the Autonomous Terminal:

**Properties**
```bash
gemini.api.key=your_api_key_here
```

## ▶️ Run the Application

### Backend (Java Spring Boot)
- Open the ems-backend folder in Eclipse or IntelliJ.
- Update dependencies via Maven.
- Run EmsBackendApplication.java. The server will boot on localhost:8080.

### Frontend (UI)
- Open the ems-frontend folder in VS Code.
- Launch login.html using the Live Server extension.
- Log in with Boss-level credentials to access the Executive Console.

## 🧠 How It Works (AI Orchestration Pipeline)

```
User AI Command (God Mode Terminal)
          ↓
Java Backend Interceptor
          ↓
Gemini AI Intent Parsing (Strict JSON Format)
          ↓
Tool / Method Invocation (Spring Boot Services)
          ↓
Database Execution & Validation (MySQL)
          ↓
Secure Frontend UI Response
```

## 🛣️ Roadmap
- Multi-Tenant SaaS Architecture support
- Real-time WebSockets for global chat
- Advanced Event Sourcing (CQRS)
- Cloud Deployment (AWS / Heroku)
- Automated Invoice Generation

## 🤝 Contributing
Pull requests are welcome.

Please open an issue to discuss new enterprise features or architectural improvements.

## License
MIT License

## ⭐ Support
If you find this enterprise architecture useful, consider giving it a ⭐ on GitHub!
