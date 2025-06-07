# Template

## Setup local environment

### Dependencies

Make sure you have the following installed:

- Docker Desktop
- Node.js (v18 or later)
- Java (JDK 21 or later)

Clone the repository:

```bash
git clone ...
```

Go to the project directory to set up postgres local database and run the docker compose file : 

```bash
cd template/infrastructure/src/main/resources/db-local
docker compose up -d
```

Install frontend dependencies:

```bash
cd template/client
npm install
```
### Run the application
To run the application, you need to start both the backend and frontend servers.
#### Backend
To run the backend server, run the main-class on your favorite IDE 
```com/gloryConnect/app/infrastructure/GloryConnectApplication.kt```

#### Frontend
To run the frontend server, navigate to the client directory and run:

```bash
cd template/client
npm start
```

If you want to add Variable environment to your project, please send an email to ```johan.ramaroson@gmail.com``` to add it into