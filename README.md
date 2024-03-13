# Monster Hunter Database

## Overview
This project is a database application for managing information about monsters in the game Monster Hunter. It allows for the creation, reading, updating, and deletion of data related to monsters, elements, and locations in the game.

## Requirements
To run this project, you'll need the following:

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- PostgreSQL database (or another JPA compatible database)
- IntelliJ IDEA (or another Java compatible IDE)

## Database Configuration
Before running the application, make sure to configure the database correctly:

1. Create a database on your PostgreSQL server.
2. Open the `persistence.xml` file in the `src/main/resources/META-INF` directory.
3. Edit the connection URL, username, and password to match your database configuration:

   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/your_database_name"/>
   <property name="javax.persistence.jdbc.user" value="your_username"/>
   <property name="javax.persistence.jdbc.password" value="your_password"/>
