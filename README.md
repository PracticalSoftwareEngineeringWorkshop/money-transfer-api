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

### Travis CI - Continuous Integration
Installing the Travis CI command line  
`sudo gem install travis --no-document`

#### Notes on Travis CI
[Travis CI Build Pipeline](https://www.baeldung.com/travis-ci-build-pipeline)  
[CI/CD Spring Boot App using Travis CI](https://www.javacodegeeks.com/2018/01/ci-cd-springboot-applications-using-travis-ci.html)  

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
