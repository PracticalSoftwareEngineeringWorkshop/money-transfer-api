# CI Status  
Travis CI build status: [![Build Status](https://travis-ci.com/PracticalSoftwareEngineeringWorkshop/money-transfer-api.svg?branch=main)](https://travis-ci.com/PracticalSoftwareEngineeringWorkshop/money-transfer-api)

# Money Transfer API
A Spring boot application with a Postgresql database that provides an API for creating and managing accounts and money transfers.

### Versions
Spring Boot: 2.4.3  
Postgres: 10.5  
Java (jdk): 11.0.5-zulu  

### Contact Developer(s)
Biniam Asnake <biniamasnake@gmail.com>  

### Running Spring Boot app from the command line using Maven
`./mvnw spring-boot:run`  

### Building and Running Docker image
[Running a Multi-container (Spring Boot and PostgreSQL) Application with Docker Compose](https://www.section.io/engineering-education/running-a-multi-container-springboot-postgresql-application-with-docker-compose/)    
[Docker : Zero to Hero (with SpringBoot + Postgres)](https://isurunuwanthilaka.medium.com/docker-zero-to-hero-with-springboot-postgres-e0b8c3a4dccb)
[Spring Boot with PostgreSQL and Docker Compose](https://gustavopeiretti.com/spring-boot-with-postgresql-and-docker-compose/)    

`docker build -t money-transfer-api .`  -t for tagging the image with given name.  
`docker run -p 8080:8080 money-transfer-api`  

### Run the multi-container application
To test all you have been putting together, open up the terminal, navigate to the Spring Boot project directory where you have the `docker-compose.yml`, and run:    
`docker-compose up -d`  

To stop all the services in the terminal, run:  
`docker-compose down`  

### Connecting to Postgres DB running in the Docker
Option 1  
`psql postgresql://moneytransfer:m0n3ytr4n5f3r@localhost:65432/moneytransfer`  

Option 2  
`docker exec -it postgres psql -U moneytransfer`  

Option 3  
```
$ docker exec -it postgres bash
# su moneytransfer
$ psql
```  

You can spin up a Postgres DB just like:  
`docker run --rm -P -p 127.0.0.1:65432:5432 -e POSTGRES_PASSWORD="m0n3ytr4n5f3r" --name postgres postgres:13.1-alpine`  

### Code Repository
[Github](https://github.com/PracticalSoftwareEngineeringWorkshop/money-transfer-api)  
Main/Master branch name: `main`  

### App production URL, CI and Deployment
[API Production URL](https://money-transfer-api-biniam.herokuapp.com/api/)  
[Frontend App Production URL](https://money-transfer-web-app-biniam.herokuapp.com/)    
[Travis CI](https://travis-ci.com/github/PracticalSoftwareEngineeringWorkshop/money-transfer-api/)  
[Heroku](https://dashboard.heroku.com/apps/money-transfer-api-biniam/)  

### DB Scripts to execute 
Run the following script on your Postgres DB (`psql -Upostgres`).    

Create a database
`CREATE DATABASE moneytransfer;`  

Create a user with an encrypted password  
`CREATE USER moneytransfer WITH ENCRYPTED PASSWORD 'm0n3ytr4n5f3r';`  

Assign or grant privileges (such as SELECT, UPDATE, DELETE, ...) to the user for that database  
`GRANT ALL PRIVILEGES ON DATABASE moneytransfer TO moneytransfer;`  

To grant the user Login privileges
`ALTER USER moneytransfer WITH LOGIN;`

Login with the new user
`psql -h localhost -Umoneytransfer -W moneytransfer`

### Testing
[Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing)  

### GitHub Actions - automating CI/CD
[Building and testing Java with Maven](https://docs.github.com/en/actions/guides/building-and-testing-java-with-maven)  
[GitHub Action Deploy to Heroku](https://github.com/marketplace/actions/deploy-to-heroku)  
[Github Actions - Managing complex workflows](https://docs.github.com/en/actions/learn-github-actions/managing-complex-workflows)

##### Another reading  
[Automatic deployment to Heroku CI/CD Spring Boot + Maven + Github Actions](https://www.nearsure.net/blog/automatic-deployment-to-heroku-ci-cd-spring-boot-maven-github-actions)  

### Heroku - for deploying app to production/live
Checking the application log from command line  
`heroku logs --app=money-transfer-api-biniam --tail`  

### H2 database - in memory database configuration

```yaml
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:money-transfer-db;DB_CLOSE_DELAY=-1
    username: sa
    password: sa
    # In Spring boot 2, database initialization only works for embedded databases (H2, HSQLDB).
    # If you want to use it for other databases, you need to change the
    initialization-mode: always
  jpa.show-sql: true
  jpa.hibernate.ddl-auto: update
```

## Roadmap - the next things to do
1. Integrate Spring Security (with OAuth)
2. App version, branch and committer display 
3. Dockerize the application
4. Test for TransferController
5. Integrate Swagger/OpenApiSpecification API documentation

#### Other notes
[Writing Markdown document such as this read me file](https://www.markdownguide.org/basic-syntax/)

## Deprecated
### Travis CI - Continuous Integration
Installing the Travis CI command line  
`sudo gem install travis --no-document`

#### Notes on Travis CI
[Travis CI Build Pipeline](https://www.baeldung.com/travis-ci-build-pipeline)  
[CI/CD Spring Boot App using Travis CI](https://www.javacodegeeks.com/2018/01/ci-cd-springboot-applications-using-travis-ci.html)  
