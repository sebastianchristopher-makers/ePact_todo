Migrations:
```
CREATE DATABASE ePactToDoApp;
```
```
CREATE DATABASE ePactToDoAppTest;
```
 create tables for both databases:
 ```
CREATE TABLE todo(id SERIAL PRIMARY KEY, content VARCHAR(100), complete BOOLEAN, userId INTEGER, labelId INTEGER);
```
```
CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(20), password VARCHAR(60));
```