# Template KT

This repository provides a script to generate a quickly bootstrapping a full-stack application with a Kotlin (Spring Boot) backend and an Angular frontend.

## Features
- **Kotlin/Spring Boot** backend (modularized: domain, infrastructure)
- **Angular** frontend (client)
- Docker Compose for local PostgreSQL database
- Gradle build system (Kotlin DSL)
- Pre-configured for easy package renaming and project customization

## Generated Architecture
- **Backend**: Ready-to-use hexagonal architecture, with a clear separation between the domain (business logic) and infrastructure (data access, external services, etc.).
- **Frontend**: Angular application generated in a dedicated folder (`client`).
- **Hosting**: The Spring Boot server also hosts the Angular frontend, enabling unified deployment.

## Prerequisites
- Java 21+
- Node.js 18+
- Docker Desktop
- Git
- Python 3.8+

## Getting Started

### 1. Clone the repository
```bash
git clone <your-repo-url>
cd <your-repo-folder>
```

### 2. Generate your project from the template
Run the script and follow the prompts to set your project name, main package, and destination folder:
```bash
python script.py
```

### 3. Set up the local database
```bash
cd <your-project>/infrastructure/src/main/resources/db-local
docker compose up -d
```

### 4. Install frontend dependencies
```bash
cd <your-project>/client
npm install
```

## Running the Application

### Backend
Open the main application class in your IDE and run it (e.g. `YourMainApplication.kt` in the infrastructure module).

### Frontend
```bash
cd <your-project>/client
npm start
```

## Customization
- The script will automatically replace all template names and packages with your chosen values.
- You can further customize the modules as needed for your business logic.



## Future Improvements
###  Version Selection
In a future version, it will be possible to choose the versions of the technologies (Kotlin, Spring Boot, Angular, etc.) when generating the project.

### Use better templating
Currently, the script uses basic string replacement. In the future, it could be improved with a more robust templating engine to handle complex replacements and configurations.

### Technologies

For now, technologies are fixed,  but in the future, it will be possible to choose between different frameworks and libraries (such as Ktor, Quarkus, etc. for the backend and React, Vue.js, etc. for the frontend).

## License
This project is licensed under the Apache License 2.0.

