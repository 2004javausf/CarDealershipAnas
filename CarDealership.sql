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


CREATE TABLE CUSTOMERS(
CUSTOMER_ID INTEGER PRIMARY KEY,
USER_NAME VARCHAR(20),
USER_PASSWORD VARCHAR(20),
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20));


CREATE TABLE CARS(
CAR_ID INTEGER PRIMARY KEY,
CAR_MAKE VARCHAR2(20),
CAR_MODEL VARCHAR2(20),
CAR_COLOR VARCHAR2(20),
CAR_STATUS VARCHAR(20),
CAR_PRICE DOUBLE PRECISION);


CREATE TABLE OFFERS(
OFFER_ID INTEGER PRIMARY KEY,
CUSTOMER VARCHAR2(20),
CAR_ID INTEGER,
CAR_PRICE DOUBLE PRECISION,
DWN_PMT DOUBLE PRECISION,
MNTHLY_PMT DOUBLE PRECISION,
PMT_LEFT INTEGER,
OFFER_STAT VARCHAR2(20));


/*******************************************************************************
   CREATE SEQUENCES
********************************************************************************/

CREATE SEQUENCE CUSTSEQ;
CREATE SEQUENCE CARSEQ;
CREATE SEQUENCE OFFERSEQ; 

/*******************************************************************************
   Alter Tables
********************************************************************************/

ALTER TABLE CUSTOMERS
ADD CONSTRAINT UQ_USER_NAME
UNIQUE(USER_NAME);

/*******************************************************************************
   Procedures
********************************************************************************/

-- PROCEDURE TO DELETE A CAR FROM THE LOT
CREATE OR REPLACE PROCEDURE DELETECAR(ID IN INTEGER)
AS
BEGIN 
DELETE FROM CARS WHERE CAR_ID = ID;
COMMIT;
END;
/

-- PROCEDURE TO ACCEPT A CUSTOMER OFFER
CREATE OR REPLACE PROCEDURE ACCEPTOFFER(OFFERED_ID INT, CAR_OFFER_ID INT)
AS
BEGIN
UPDATE OFFERS SET OFFER_STAT = 'Accepted' WHERE OFFER_ID = OFFERED_ID;
UPDATE CARS SET CAR_STATUS = 'Owned'
WHERE CAR_ID = (SELECT CAR_ID FROM OFFERS WHERE CAR_ID = CAR_ID);
DELETE FROM OFFERS WHERE (OFFER_STAT = 'Pending' AND CAR_ID = CAR_OFFER_ID);
COMMIT;
END;
/

-- PROCEDURE TO REJECT A CUSTOMER OFFER
CREATE OR REPLACE PROCEDURE REJECTOFFER(OFFERED_ID INT, CAR_OFFER_ID INT)
AS
BEGIN
DELETE FROM OFFERS WHERE (OFFER_ID = OFFERED_ID AND CAR_ID = CAR_OFFER_ID);
COMMIT;
END;
/


