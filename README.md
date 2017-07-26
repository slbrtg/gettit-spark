# _Gettit App_

#### _An app that is based off of Reddit..., July 24, 2017_

#### By _**Sowmya Logaswamy, Mara Timberlake, Saul Artiga, Lincoln Nguyen**_

## Description
_Gettit... like Reddit_

## What's included
Within the repository you'll find the following directories and files:

```
gettit-spark/
├── src/
│    └── main/
│    │    └── java/
|    │    │     └── App.java
|    │    │     └── Comment.java
|    │    │     └── DB.java
|    │    │     └── Post.java
|    │    │     └── Sub.java
|    │    │     └── User.java
|    │    │     └── VelocityTemplateEngine.java
|    |    └── resources/
|    |          └──public/
|    |          |    └──css/
|    |          |    |   └──app.css
|    |          |    |   └──bootstrap.css
|    |          |    └──images/
|    |          |         └──folderinfo.txt
|    |          └──templates/
|    |               └──index.vtl
|    |               └──layout.vtl
|    └── test/
│         └── java/
|               └── CommentTest.java
|               └── DatabaseRule.java
|               └── PostTest.java
|               └── SubTest.java
|               └── UserTest.java
├── .gitignore
├── build.gradle
├── gettit.sql
└── README.md
```

## Setup/Installation Requirements
To create the necessary databases:
* _LOCAL: Go to Terminal_
* _Clone this repository:_
```
$ cd ~/Desktop
$ git clone https://github.com/slbrtg/gettit-spark.git
$ cd gettit-spark
```
* _Run Postgres with terminal command:_
```
$ postgres
```
* _Open a new tab in terminal by pressing [command ⌘] + [T]_
* _In the new tab, create 'gettit_spark' database:_
```
$ psql
* `CREATE DATABASE gettit;`
* `\c gettit;`
* `CREATE TABLE users (id serial PRIMARY KEY, username varchar unique, password varchar);`
* `CREATE TABLE posts (id serial PRIMARY KEY, type varchar, title varchar, content varchar, subName varchar, votes int, time timestamp, username varchar, glyph varchar);`
* `CREATE TABLE comments (id serial PRIMARY KEY, comment varchar, postId int, username varchar, time timestamp);`
* `CREATE TABLE subs (id serial PRIMARY KEY, name varchar unique, description varchar, modId int);`
* `CREATE DATABASE gettit_test WITH TEMPLATE gettit;`
```
* _Return to original tab where repository was cloned and run gradle:_
```
$gradle run
```
* _Open browser window:_
```
localhost:4567
```
## Specifications
_App recreates..._

## Known Bugs
_No known bugs at this time_

## Support and contact details
_For questions or feedback, please notify Sowmya Logaswamy at sowmyalogaswamy@gmail.com , Mara Timberlake at maratimberlake@msn.com, Saul Artiga at slbrtg@gmail.com, and Lincoln Nguyen at huylincoln007@yahoo.com_

## Technologies Used
_Languages used include Java. IDE used - Atom_

### License
*This software runs under the MIT license*

Copyright (c) 2017 **_MIT License_**
