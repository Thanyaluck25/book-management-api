# Book Management API
## Setup Instructions

1. ### Database Setup:
   - Install MySQL and create a `book_management` database and run the following SQL commands to set up the `book` table:

    ```sql
    CREATE DATABASE book_management;
    CREATE TABLE book (
        id BIGINT  AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        published_date DATE NOT NULL
    );
    ```
   - Configure Database Connection: Ensure that your `application.properties` file in the `src/main/resources` folder is set to connect to your database:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/book_management
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD_HERE
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   ```
   
2. ### Running the Server:
    - Clone the repository:
    ```bash
    git clone git clone https://github.com/your-repository/bookmanagement.git
    cd bookmanagement
    ```

    - Build and Run the Project:
    ```bash
   mvn clean install
   mvn spring-boot:run
    ```

3. ### Running the Integration Tests:
   - Make sure your MySQL database is set up and the application properties are configured properly to connect to it.
    - Use Maven to run the tests:
    ```bash
    ./mvnw test
    ```

4. ### Example API Requests and Expected Responses:

   **Save Book** (POST `/books`):

   URL: `http://localhost:8080/books`

   BODY(JSON):
    ``` json
   {
      "title": "Spring Boot",
      "author": "Than",
      "publishedDate": "2567-12-24"
   }
    ```
   **Expected Response:**
   ``` json
   {
      "title": "Spring Boot",
      "author": "Than",
      "publishedDate": "2024-12-24"
   }
    ```
   ###### The publishedDate is received in the Buddhist calendar format (2567-12-24), and the response will show the converted Gregorian date (2024-12-24).

   **Get Books by Author** (GET `/books?author=Than`):

   **Expected Response:**
    ``` json
   [
      {
         "id" : 1,
         "title": "Spring Boot",
         "author": "Than",
         "publishedDate": "2024-12-24"
      }
   ]
    ```