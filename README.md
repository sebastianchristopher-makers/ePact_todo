Migrations:
```
CREATE DATABASE ePactToDoApp;
```
```
CREATE DATABASE ePactToDoAppTest;
```
 create table for both databases:
 ```
CREATE TABLE todo(id SERIAL PRIMARY KEY, content VARCHAR(100), complete BOOLEAN, userId INTEGER, labelId INTEGER);
```
