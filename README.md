# EMPYREAN-OS — Enterprise Business Operating System & HCM

![Java](https://img.shields.io/badge/Java-Spring_Boot-green?logo=spring)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)
![WebSockets](https://img.shields.io/badge/RealTime-WebSockets-orange)
![Frontend](https://img.shields.io/badge/Frontend-Vanilla_JS-yellow?logo=javascript)
![Architecture](https://img.shields.io/badge/Architecture-MVC-purple)
![DataVis](https://img.shields.io/badge/Data_Vis-D3.js_%7C_Chart.js-ff69b4)
![Status](https://img.shields.io/badge/Project-Active-success)
![License](https://img.shields.io/badge/License-MIT-yellow)

EmpyreanOS is a full-scale, next-generation Human Capital Management (HCM) and Business Operating System. It goes beyond basic CRUD operations to provide a living, real-time workspace for executives, managers, and employees.

The project combines a robust Java Spring Boot backend with a dynamic, role-based Vanilla JavaScript frontend. It focuses on real-world enterprise system design, featuring live socket communications, automated payroll engines, strict role-based access control (RBAC), and integrated operational ledgers.

--- 

## 🎯 Project Objective

The primary goal of EmpyreanOS is to design and implement a scalable, modular corporate framework that can:
* Dynamically route and restrict UI access based on corporate hierarchy (Boss, Manager, Employee).
* Automate complex HR workflows, including pro-rated payroll, tax deductions, and document generation.
* Provide real-time internal communications (1-on-1 and Group Spaces) to replace external tools like Slack.
* Manage physical and digital assets securely through a unified Management Hub[cite.
* Serve as a comprehensive portfolio piece showcasing end-to-end full-stack system integration.

---

## 🚀 Key Features

* **Dynamic Role-Based Access (RBAC):** The frontend architecture dynamically injects UI components, ensuring Employees only see personal data, Managers see team data, and the Boss has full executive oversights.
* **Automated Payroll Run Engine:** A complex calculation engine that pulls attendance and leave data to generate pro-rated salaries, calculating Tax (TDS), PF, and Medical deductions, and exporting PDF payslips.
* **Real-Time WebSockets Chat:** Live communication module supporting direct messages and custom Group Spaces, complete with Base64 secure file attachments (PDF/Images) and message history.
* **Unified Management Hub:** A consolidated control center for Managers to assign hardware, approve expense reimbursements, upload to a secure Document Vault, and save 1:1 private coaching notes.
* **Intelligent Time & Attendance:** Live clock-in widgets with a backend algorithm that automatically fills historical gaps with "Absent" or "Weekend" statuses.
* **Interactive D3.js Org Chart:** A visual, infinite-pan hierarchy tree mapping out the entire corporate structure from the CEO down to interns.

---

## 🧠 Practical Use Cases

* **Startup Operations:** A complete out-of-the-box system for managing a growing company's HR and payroll needs.
* **Internal Communications:** Secure, self-hosted corporate messaging without relying on third-party vendors.
* **Hardware & Expense Auditing:** A permanent ledger tracking company laptops, phones, and approved financial reimbursements.
* **Full-Stack Architecture Demonstration:** An advanced academic or portfolio project showcasing complex API routing, WebSocket streams, and relational database mapping.

---

## 🏗️ High-Level System Architecture

EmpyreanOS follows a strict Controller-Service-Repository pattern on the backend, seamlessly connected to a Single-Source-of-Truth frontend layout.

### Core Layers

#### 1. The UI Layer (Frontend)
* Pure HTML/CSS/JS for lightning-fast DOM manipulation.
* `sidebar.js` acts as the master router, building navigation dynamically based on local storage roles[cite: 6, 80].

#### 2. The Logic Layer (Backend)
* Java Spring Boot REST Controllers handle API requests.
* Services contain business rules (e.g., calculating net salary or recording audit logs).
* WebSocket configuration manages STOMP protocol for live chat channels.

#### 3. The Data Layer (Database)
* MySQL database with complex entity mapping (Employees, SalaryRecords, ChatMessages, Assets, Leaves) managed via Spring Data JPA.

---

## 📁 Project Structure

```text
EmpyreanOS/
│
├── ems-backend/
│   ├── .metadata/
│   ├── .mvn/
│   ├── .plugins/
│   │
│   ├── src/
│   │   └── main/
│   │       └── java/com/company/ems/
│   │
│   │           ├── config/
│   │           │   └── WebSocketConfig.java
│   │
│   │           ├── controller/
│   │           │   ├── AttendanceController.java
│   │           │   ├── AuthController.java
│   │           │   ├── ChatController.java
│   │           │   ├── EmployeeController.java
│   │           │   ├── EventController.java
│   │           │   ├── LeaveController.java
│   │           │   ├── ManagerController.java
│   │           │   ├── SalaryController.java
│   │           │   └── WorkspaceController.java
│   │
│   │           ├── dto/
│   │           │   └── LoginRequest.java
│   │
│   │           ├── model/
│   │           │   ├── Asset.java
│   │           │   ├── Attendance.java
│   │           │   ├── AuditLog.java
│   │           │   ├── ChatMessage.java
│   │           │   ├── Employee.java
│   │           │   ├── EmployeeDocument.java
│   │           │   ├── Event.java
│   │           │   ├── ExpenseRequest.java
│   │           │   ├── LeaveRecord.java
│   │           │   ├── ManagerNote.java
│   │           │   └── SalaryRecord.java
│   │
│   │           ├── repository/
│   │           │   ├── AssetRepository.java
│   │           │   ├── AttendanceRepository.java
│   │           │   ├── AuditLogRepository.java
│   │           │   ├── ChatMessageRepository.java
│   │           │   ├── EmployeeDocumentRepository.java
│   │           │   ├── EmployeeRepository.java
│   │           │   ├── EventRepository.java
│   │           │   ├── ExpenseRepository.java
│   │           │   ├── LeaveRepository.java
│   │           │   ├── ManagerNoteRepository.java
│   │           │   └── SalaryRepository.java
│   │
│   │           ├── service/
│   │           │   ├── AttendanceService.java
│   │           │   ├── AuditLogService.java
│   │           │   ├── AuthService.java
│   │           │   ├── EmployeeService.java
│   │           │   ├── LeaveService.java
│   │           │   ├── SalaryService.java
│   │           │   └── EmsBackendApplication.java
│   │
│   │   ├── resources/
│   │   │   └── application.properties
│   │
│   │   └── test/
│   │
│   ├── target/
│   ├── .classpath
│   ├── .project
│   └── pom.xml
│
├── ems-frontend/
│   ├── .vscode/
│   │
│   ├── admin.html
│   ├── attendance.html
│   ├── calendar.html
│   ├── chat.html
│   ├── employees.html
│   ├── index.html
│   ├── leaves.html
│   ├── login.html
│   ├── register.html
│   ├── payroll-engine.html
│   ├── payroll.html
│   ├── reports.html
│   ├── script.js
│   ├── settings.html
│   ├── sidebar.js
│   ├── style.css
│   ├── team.html
│   └── workspace.html
│
├── .lock
├── .log
├── version.ini
└── .project
```

---


---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/arshdeepsarsh/EmpyreanOS.git
cd EmpyreanOS
```
