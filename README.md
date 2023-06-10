
# Spring boot backend

This project was made using spring boot 3.1.0 it is a framework in java using java 17, all dependencies are in pom.xml only run the dependencies.


## Description

This project it's a backend using the next technologies:

- MySQL - Database
- JWT - For sessions
- Password4j - For encrypt passwords
- EntityManager - ORM
- Interceptors - For Autorizathion user

The project has the next enpoints:

- /login - For make a login session to the user
- /users/register - For register users
- /users/all - Get all users in DB
- /users/delete - Delete users using id in the body

The project has only one table you will see the table in the model folder and with that table you can handle the sessions and all endpoints




## Deployment

Using the plugin from VSC or using IntellJ IDE for run your java project

## Installation

For run the project successfully you need to create the folder "resources" and inside this folder add the application.properties, put the next information inside this file

```bash
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/#DatabaseName
    spring.datasource.username=#UserDB
    spring.datasource.password=#PasswordDB
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    #JWT
    security.jwt.secret=#CustomSecretJWT
    security.jwt.issuer=#CustomIssuerJWT
    #session duration
    security.jwt.ttl=#DurationJWTinMillis
```
    
Remember this file is ignore by gitignore because has a important information