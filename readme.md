# István Fekete's Animal Characters

A web application showcasing the beloved animal characters from István Fekete's novels.

## Project Overview

This Spring Boot application serves as a digital library for the animal characters featured in the works of Hungarian author István Fekete. The website allows users to explore information about the characters, the novels they appear in, and learn more about the author.

## Features

- **Browse Animals**: View detailed information about each animal character
- **Browse Novels**: Explore István Fekete's novels and their summaries
- **User Authentication**: Register and login system
- **Admin Dashboard**: Manage users, animal entries, and novels
- **Contact Form**: Send messages to the administrators
- **Responsive Design**: Bootstrap-based interface that works on all devices

## Technology Stack

- **Backend**: Spring Boot, Spring Security, Spring MVC
- **Frontend**: Thymeleaf, Bootstrap 5
- **Database**: Not specified in provided files (likely JPA/Hibernate)
- **Authentication**: Spring Security

## Project Structure

- **Controllers**: Handle HTTP requests and manage views
  - `AdminController`: Manages admin dashboard access
  - `AuthController`: Handles user authentication and registration
- **Models**: Object representations of data
  - `User`: Represents registered users
- **Services**: Business logic
  - `UserService`: Manages user-related operations
- **Templates**: Thymeleaf HTML templates
  - Layout template with common page elements
  - Admin dashboard
  - Authentication pages

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven or Gradle (build tool)
- A compatible database system

### Installation

1. Clone the repository
2. Configure your database settings in `application.properties`
3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
   or Gradle:
   ```
   gradle bootRun
   ```
4. Access the application at `http://localhost:8080`

## Security

The application implements role-based access control:
- **ADMIN**: Full access to the admin dashboard and all management functions
- **USER**: Can view content and access their messages
- **Anonymous**: Can browse public content and register

## Contributing

Guidelines for contributing to this project would go here.

## License

Information about the project license would go here.

## Acknowledgments

- István Fekete for his wonderful literary works
- All contributors to the project