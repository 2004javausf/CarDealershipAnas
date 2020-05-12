/*******************************************************************************
   CarDealership Database - Version 1.0
   Script: CarDealership.sql
   Description: Creates the Car Dealership database.
   DB Server: Oracle
   Author: Anas Mohamad Daud
********************************************************************************/

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
DROP USER CarDealership CASCADE;

/*******************************************************************************
   Create Database
********************************************************************************/
CREATE USER anasdaud
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to anasdaud;
GRANT resource to anasdaud;
GRANT create session TO anasdaud;
GRANT create table TO anasdaud;
GRANT create view TO anasdaud;

conn anasdaud/p4ssw0rd

/*******************************************************************************
   Create Tables
********************************************************************************/

CREATE TABLE EMPLOYEES(
EMPLOYEE_ID INTEGER PRIMARY KEY,
USER_NAME VARCHAR2(20),
EMP_PASSWORD VARCHAR(20));

INSERT INTO EMPLOYEES VALUES(1,'Anas','password');

CREATE TABLE CUSTOMERS(
CUSTOMER_ID INTEGER PRIMARY KEY,
USER_NAME VARCHAR(20),
USER_PASSWORD VARCHAR(20),
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20));

INSERT INTO CUSTOMERS VALUES(CUSTSEQ.NEXTVAL, 'matt34','password','Matt','Joss');

CREATE TABLE CARS(
CAR_ID INTEGER PRIMARY KEY,
CAR_MAKE VARCHAR2(20),
CAR_MODEL VARCHAR2(20),
CAR_COLOR VARCHAR2(20),
CAR_STATUS VARCHAR(20),
CAR_PRICE DOUBLE PRECISION);

DROP TABLE CARS;
INSERT INTO CARS VALUES(1, 'Lamborghini','Huracan','Yellow','Available',450000);
INSERT INTO CARS VALUES(2, 'Ferrari','Roma','Silver','Available',360000);
INSERT INTO CARS VALUES(3, 'Porshe','Turismo','White','Available',157000);
INSERT INTO CARS VALUES(4, 'Ferrari','Roma','Silver','Available',360000);
INSERT INTO CARS VALUES(5, 'Lamborghini','Aventador S','Green','Available',500000);
INSERT INTO CARS VALUES(6, 'Bugatti','Veyron Sport','SkyBlue','Available',900000);

CREATE TABLE OFFERS(
OFFER_ID INTEGER PRIMARY KEY,
CUSTOMER VARCHAR2(20),
CAR_ID INTEGER,
CAR_PRICE DOUBLE PRECISION,
DWN_PMT DOUBLE PRECISION,
MNTHLY_PMT DOUBLE PRECISION,
PMT_LEFT INTEGER,
OFFER_STAT VARCHAR2(20));

INSERT INTO OFFERS VALUES(1,'maria98',1,450000,90000,6000,60,'Accepted');

DELETE
FROM
    OFFERS
WHERE
    OFFER_ID = 1;
/*******************************************************************************
   CREATE SEQUENCES
********************************************************************************/

CREATE SEQUENCE CUSTSEQ;
CREATE SEQUENCE CARSEQ;
CREATE SEQUENCE OFFSEQ; 

/*******************************************************************************
   Alter Tables
********************************************************************************/

ALTER TABLE CUSTOMERS
ADD CONSTRAINT UQ_USER_NAME
UNIQUE(USER_NAME);

ALTER TABLE OFFERS
ADD CONSTRAINT FK_CUSTOMER_USER_NAME
FOREIGN KEY (CUSTOMER) REFERENCES CUSTOMERS(USER_NAME);

ALTER TABLE OFFERS
ADD CONSTRAINT FK_CAR_CAR_ID
FOREIGN KEY (CAR_ID) REFERENCES CARS(CAR_ID);



/*******************************************************************************
   Procedures
********************************************************************************/

CREATE OR REPLACE PROCEDURE DELETECAR(ID IN INTEGER)
AS
BEGIN 
DELETE FROM CARS WHERE CAR_ID = ID;
COMMIT;
END;
/
