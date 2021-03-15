Travis CI build status: [![Build Status](https://travis-ci.com/PracticalSoftwareEngineeringWorkshop/money-transfer-api.svg?branch=main)](https://travis-ci.com/PracticalSoftwareEngineeringWorkshop/money-transfer-api)

# Money Transfer API

### DB Scripts  
Run the following script on your Postgres DB (`psql -Upostgres`).    

Create a database
`CREATE DATABASE moneytransfer;`  

Create a user with an encrypted password  
`CREATE USER moneytransfer WITH ENCRYPTED PASSWORD 'm0n3ytr4n5f3r';`  

Assign or grant privileges (such as SELECT, UPDATE, DELETE, ...) to the user for that database 
`GRANT ALL PRIVILEGES ON DATABASE moneytransfer TO moneytransfer;`  

