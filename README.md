# CAREGIVER APP

## Description
The Caregiver App is a web application designed to assist caregivers in managing their tasks and responsibilities. The backend is built using Spring Boot and MongoDB, while the frontend is developed with React.

## Requirements
- Java 22 or higher
- MongoDB
- Node.js 20 or higher
- npm 6 or higher
- 

## Running the Backend

1. **Install Dependencies**:
    ```bash
    mvn clean install
    ```

2. **Set Up the Database with Docker**:
    ```bash
    docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
    ```

2. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

## Running the Frontend

Run the following commands in the `frontend` directory:

1**Install Dependencies**:
    ```bash
    npm install
    ```

2**Start the Development Server**:
    ```bash
    npm start
    ```

