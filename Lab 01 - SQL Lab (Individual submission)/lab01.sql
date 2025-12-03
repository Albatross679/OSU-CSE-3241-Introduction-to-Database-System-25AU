--
-- File generated with SQLiteStudio v3.4.17 on Wed Oct 22 17:15:37 2025
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: DEPARTMENT
CREATE TABLE IF NOT EXISTS DEPARTMENT (Dname varchar (15) NOT NULL, Dnumber int NOT NULL, Mgr_ssn char (9) NOT NULL, Mgr_start_date date, PRIMARY KEY (Dnumber), FOREIGN KEY (Mgr_ssn) REFERENCES EMPLOYEE (Ssn) ON UPDATE CASCADE);
INSERT INTO DEPARTMENT (Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES ('Research', 5, '333445555', '5/22/1988');
INSERT INTO DEPARTMENT (Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES ('Administration', 4, '987654321', '1/1/1995');
INSERT INTO DEPARTMENT (Dname, Dnumber, Mgr_ssn, Mgr_start_date) VALUES ('Headquarters', 1, '888665555', '6/19/1981');

-- Table: DEPENDENT
CREATE TABLE IF NOT EXISTS DEPENDENT (Essn char (9) NOT NULL, Dependent_name varchar (15) NOT NULL, Sex char NOT NULL, Bdate date NOT NULL, Relationship varchar (15) NOT NULL, PRIMARY KEY (Essn, Dependent_name), FOREIGN KEY (Essn) REFERENCES EMPLOYEE (Ssn) ON UPDATE CASCADE);
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('333445555', 'Alice', 'F', '4/5/1986', 'Daughter');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('333445555', 'Theodore', 'M', '10/25/1983', 'Son');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('333445555', 'Joy', 'F', '5/3/1958', 'Spouse');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('987654321', 'Abner', 'M', '2/28/1942', 'Spouse');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('123456789', 'Michael', 'M', '1/4/1988', 'Son');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('123456789', 'Alice', 'F', '12/30/1988', 'Daughter');
INSERT INTO DEPENDENT (Essn, Dependent_name, Sex, Bdate, Relationship) VALUES ('123456789', 'Elizabeth', 'F', '5/5/1967', 'Spouse');

-- Table: DEPT_LOCATIONS
CREATE TABLE IF NOT EXISTS DEPT_LOCATIONS (Dnumber int NOT NULL, Dlocation varchar (15) NOT NULL, PRIMARY KEY (Dnumber, Dlocation), FOREIGN KEY (Dnumber) REFERENCES DEPARTMENT (Dnumber) ON UPDATE CASCADE);
INSERT INTO DEPT_LOCATIONS (Dnumber, Dlocation) VALUES (1, 'Houston');
INSERT INTO DEPT_LOCATIONS (Dnumber, Dlocation) VALUES (4, 'Stafford');
INSERT INTO DEPT_LOCATIONS (Dnumber, Dlocation) VALUES (5, 'Bellaire');
INSERT INTO DEPT_LOCATIONS (Dnumber, Dlocation) VALUES (5, 'Sugarland');
INSERT INTO DEPT_LOCATIONS (Dnumber, Dlocation) VALUES (5, 'Houston');

-- Table: EMPLOYEE
CREATE TABLE IF NOT EXISTS EMPLOYEE (Fname varchar (15) NOT NULL, Minit char (1), Lname varchar (15) NOT NULL, Ssn char (9) NOT NULL, Bdate date, Address varchar (30), Sex char (1), Salary int, Super_ssn char (9), Dno int NOT NULL, PRIMARY KEY (Ssn), FOREIGN KEY (Super_ssn) REFERENCES EMPLOYEE (Ssn), FOREIGN KEY (Dno) REFERENCES DEPARTMENT (Dnumber) ON UPDATE CASCADE);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('James', 'E', 'Borg', '888665555', '11/10/1937', '450 Stone, Houston, TX', 'M', 55000, 'NULL', 1);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Jennifer', 'S', 'Wallace', '987654321', '6/20/1941', '291 Berry, Bellaire, TX', 'F', 43000, '888665555', 4);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Franklin', 'T', 'Wong', '333445555', '12/8/1955', '638 Voss, Houston, TX', 'M', 40000, '888665555', 5);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Ahmad', 'V', 'Jabbar', '987987987', '3/29/1969', '980 Dallas, Houston, TX', 'M', 25000, '987654321', 4);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Joyce', 'A', 'English', '453453453', '7/31/1972', '5631 Rice, Houston, TX', 'F', 25000, '333445555', 5);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Ramesh', 'K', 'Narayan', '666884444', '9/15/1962', '975 Fire Oak, Humble, TX', 'M', 38000, '333445555', 5);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('Alicia', 'J', 'Zelaya', '999887777', '1/19/1968', '3321 Castle, Spring, TX', 'F', 25000, '987654321', 4);
INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno) VALUES ('John', 'B', 'Smith', '123456789', '1/9/1965', '731 Fondren, Houston, TX', 'M', 30000, '333445555', 5);

-- Table: PROJECT
CREATE TABLE IF NOT EXISTS PROJECT (Pname varchar (30) NOT NULL, Pnumber int NOT NULL, Plocation varchar (30) NOT NULL, Dnum int NOT NULL, PRIMARY KEY (Pnumber), FOREIGN KEY (Dnum) REFERENCES DEPARTMENT (Dnumber) ON UPDATE CASCADE);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('ProductX', 1, 'Bellaire', 5);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('ProductY', 2, 'Sugarland', 5);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('ProductZ', 3, 'Houston', 5);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('Computerization', 10, 'Stafford', 4);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('Reorganization', 20, 'Houston', 1);
INSERT INTO PROJECT (Pname, Pnumber, Plocation, Dnum) VALUES ('Newbenefits', 30, 'Stafford', 4);

-- Table: WORKS_ON
CREATE TABLE IF NOT EXISTS WORKS_ON (Essn char (9) NOT NULL, Pno int NOT NULL, Hours double, PRIMARY KEY (Essn, Pno), FOREIGN KEY (Essn) REFERENCES EMPLOYEE (Ssn) ON UPDATE CASCADE, FOREIGN KEY (Pno) REFERENCES PROJECT (Pnumber) ON UPDATE CASCADE);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('123456789', 1, 32.5);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('123456789', 2, 7.5);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('666884444', 3, 40.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('453453453', 1, 20.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('453453453', 2, 20.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('333445555', 2, 10.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('333445555', 3, 10.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('333445555', 10, 10.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('333445555', 20, 10.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('999887777', 30, 30.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('999887777', 10, 10.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('987987987', 10, 35.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('987987987', 30, 5.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('987654321', 30, 20.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('987654321', 20, 15.0);
INSERT INTO WORKS_ON (Essn, Pno, Hours) VALUES ('888665555', 20, 'NULL');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
