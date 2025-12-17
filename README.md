# Prescriptions gRPC set up
## Here's a quick tutorial on how to get this project up running without getting a headache :D
### 1. Clone the repository
```sh
git clone https://github.com/NataliaMuchova/prescriptions-grpc.git
```
### 2. Set up the databases
In the parent directory of the project you will find two files: `hospital.sql` and `pharmacy.sql`.  
Run them using postgresql, they will create the schemas for both databases  
### 3. Set up application properties  
Create the `./src/main/resources` and create within it the `application.properties` file  
Linux: 
```sh
touch ./src/main/resources/application.properties
```  
Windows (ignore the error on the last command):
```sh
md ./src/main/resources
cd ./src/main/resources
type nul > application.properties
```  
Then paste the following text, changing the value of `spring.datasource.password` to your database user password,   
into `application.properties`:  
```
spring.application.name=prescriptions-grpc

grpc.port = 9090

server.port = 8080

# Postgres database config
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=hospital
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# JPA configurations
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 4. Run the gRPC
Run the gRPC with the command:  
```sh
mvn spring-boot:run
```  
### 5. Add doctors and pharmacists  
The system allows only for registering patients if being used through the frontend/API,  
in order to add doctors and patients call gRPC directly using the method CreateUserRole
