CREATE TABLE TASK(
	ID INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(150) NOT NULL,
	LABEL_NAME VARCHAR(150) NOT NULL,
	STARTDATE DATE NOT NULL, 
        ENDDATE DATE NOT NULL, 
        PRIORITY VARCHAR(150) NOT NULL,
  CONSTRAINT fk_LABELname FOREIGN KEY (LABEL_NAME) REFERENCES LABEL (LABEL_NAME)  
);

INSERT INTO TASK VALUES (1001,'UTP', 'ProjectA', '2019-04-18','2019-04-19','HIGH');
INSERT INTO TASK VALUES (1002,'RTM', 'ProjectA', '2019-04-19','2019-04-20','LOW');
INSERT INTO TASK VALUES (1003,'TTD', 'ProjectA', '2019-04-20','2019-04-21','CRITICAL');
INSERT INTO TASK VALUES (1004,'Coding', 'ProjectA', '2019-04-21','2019-04-28','MEDIUM');
INSERT INTO TASK VALUES (1005,'UTL', 'ProjectB', '2019-04-19','2019-04-22','HIGH');
INSERT INTO TASK VALUES (1006,'VAT', 'ProjectB', '2019-04-25','2019-04-30','LOW');
INSERT INTO TASK VALUES (1007,'Sonar', 'ProjectC', '2019-04-28','2019-05-03','CRITICAL');
INSERT INTO TASK VALUES (1008,'Veracode', 'ProjectC', '2019-05-01','2019-05-09','MEDIUM');

CREATE TABLE LABEL(
	ID INT(10) NOT NULL AUTO_INCREMENT,
	LABEL_NAME VARCHAR(150) NOT NULL,
	LABEL VARCHAR(150) NOT NULL,
	PRIMARY KEY (LABEL_NAME),
);

INSERT INTO LABEL VALUES ('ProjectA', 'A');
INSERT INTO LABEL VALUES ('ProjectB', 'B');
INSERT INTO LABEL VALUES ('ProjectC', 'C');