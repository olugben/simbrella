Microservices Project: UserManagement & LoanTnxLog
This project is built with Java Spring Boot and features a microservices architecture tailored for a fintech backend system. It includes two independent services: UserManagement and LoanTnxLog, each designed to handle specific tasks.

Project Structure
UserManagement Service
Handles user registration, updates, retrievals, and deletions.

Ensures email, phone number, and password validation.

Secures passwords with BCrypt hashing.

LoanTnxLog Service
Manages loan applications, status updates, and interest calculations.

Logs loan disbursement and repayment transactions.

Validates transaction amounts and keeps balances updated.

Technologies Used
Java Spring Boot

PostgreSQL for relational data storage

JWT for authentication

Password Encoder for password hashing



Setup Instructions
Clone the repository:


git clone <https://github.com/olugben/simbrella.git>
Database Configuration:

Ensure PostgreSQL is installed and running.

Update the application.yml files in both services with your PostgreSQL credentials.
please note that the .env is left on the github for easier configuration
Run the Services:

Navigate to each service directory and run:


mvn spring-boot:run
API Documentation:


Design Decisions
Microservices Architecture: Splitting the project into two independent services enhances scalability and domain separation.

Security: Uses JWT-based authentication and role-based access control for secure service access.

Database Design: Utilizes PostgreSQL with normalized schemas for efficient data storage.

Trade-offs
No Tests Implemented: Currently, there are no unit or integration tests.
 
Future Improvements
Implement unit and integration tests using JUnit and Mockito.

Add containerization with Docker for easier deployment.



Contribution
Feel free to fork the repository and submit pull requests for improvements and additional features.

License
This project is licensed under the MIT License.