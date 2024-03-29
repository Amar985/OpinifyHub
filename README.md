# OpinifyHub
It will empower the development, distribution and analysis of survey forms to improve communication, process management, and decision support.
The user who can add questions and surveys and view the survey results and the guest user who can answer those surveys.

To access the survey created you need to share survey code of 5 characters with your actor.
## Prerequisites
- Install MySQL
- Download MySQL Connector (attached with in the repository i.e. mysql-connector-j-8.0.32.jar )
- Open properties 
  - navigate classpath of the connector library
  - add External JARs
- Setup the database and form the necessary tables by using code given below.

### Create the Database:
`CREATE DATABASE opinifyhub;`
### Use the database:
`USE opinifyhub;`
### Create necessary tables:

```
---Actor table
CREATE TABLE actors(id int primary key auto_increment, fname varchar(50),
 uname varchar(50), pass varchar(50));

---User Question Table
CREATE TABLE userQuestions(id int, surveycode varchar(5), total int);

---Questions table
CREATE TABLE questions(surveycode varchar(5), question varchar(255), 
option1 varchar(255), option2 varchar(255), option3 varchar(255), 
option4 varchar(255)); 

---Survey Answer table
CREATE TABLE surveyquestions(surveycode varchar(5), qno int, opno int);
```


## Preview

![oh1](https://github.com/Amar985/OpinifyHub/assets/84828275/d56e06ea-6dd7-4d63-9f09-3c92798ee8d9)
![oh2](https://github.com/Amar985/OpinifyHub/assets/84828275/0dd97c06-0c7a-4cf9-83cc-f922a287b06e)
![oh3](https://github.com/Amar985/OpinifyHub/assets/84828275/0309b47b-9c76-4ab9-8fdf-9082adcdd086)
![oh4](https://github.com/Amar985/OpinifyHub/assets/84828275/66bf08e2-fb3c-4aeb-9935-7801c6ace02f)
