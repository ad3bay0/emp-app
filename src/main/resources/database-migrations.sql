CREATE DATABASE IF NOT EXISTS
USE emp;


CREATE TABLE IF NOT EXISTS person(
pid int(5) NOT NULL AUTO_INCREMENT,
username varchar(100) NOT NULL,
password varchar(100) NOT NULL,
age int(11) NOT NULL,
gender char(1) NOT NULL,
city varchar(100) NOT NULL,
PRIMARY KEY (pid)

) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

INSERT INTO TABLE person (pid,username,password,age,gender,city) VALUES
(1,'adebayo','test','20','M','Lagos'),
(2,'alabi','test','24','M','Abuja');


