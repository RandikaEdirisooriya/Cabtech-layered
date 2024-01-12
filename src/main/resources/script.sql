DROP DATABASE CABTECH;
CREATE DATABASE CABTECH;
USE CABTECH;


CREATE TABLE admin (
                       adminId VARCHAR(30) PRIMARY KEY,
                       username VARCHAR(50),
                       password VARCHAR(50),
                       email VARCHAR(55)
);
Insert into admin values('A001','Randika',123,'rabe@gmail.com');

CREATE TABLE customer (
                          customerId VARCHAR(30) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL DEFAULT 'Unknown',
                          address TEXT,
                          nic_number VARCHAR(50),
                          contactNumber VARCHAR(50),
                          adminId VARCHAR(30),
                          CONSTRAINT FOREIGN KEY (adminId) REFERENCES admin(adminId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE employee(
                         EmpId VARCHAR(30) PRIMARY KEY,
                         name VARCHAR(50) NOT NULL DEFAULT 'Unknown',
                         address TEXT,
                         nic_number VARCHAR(50),
                         contactNumber VARCHAR(50),
                         jobRole VARCHAR(50),
                         salary DOUBLE DEFAULT 0.00,
                         adminId VARCHAR(30),
                         price VARCHAR(30),
                         img mediumblob,
                         Status VARCHAR(30),
                         CONSTRAINT FOREIGN KEY (adminId) REFERENCES admin(adminId) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE attendance(
                           attendanceId VARCHAR(50) PRIMARY KEY,
                           date DATE,
                           status TEXT,
                           time VARCHAR(30),
                           empId VARCHAR(30),
                           CONSTRAINT FOREIGN KEY (empId) REFERENCES employee(empId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE vehiclePartsOrder (
                                   partOrderId VARCHAR(30) PRIMARY KEY,

                                   orderDate VARCHAR(15),
                                   orderTime VARCHAR(15),
                                   cost DOUBLE DEFAULT 0.00,
                                   customerId VARCHAR(30),
                                   CONSTRAINT  FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE vehicleParts(
                             partsId VARCHAR(30) PRIMARY KEY,
                             qty INT,
                             code VARCHAR(30),
                             colour VARCHAR(35),
                             description TEXT,
                             nameOfVehiclePart VARCHAR(30),
                             Price DOUBLE DEFAULT 0.00
);

CREATE TABLE vehiclePartsOrderDetails (
                                          partOrderId VARCHAR(30),
                                          partsId VARCHAR(30),


                                          qtyForSale INT,
                                          Price DOUBLE DEFAULT 0.00,
                                          CONSTRAINT FOREIGN KEY (partsId) REFERENCES vehicleParts(partsId) ON DELETE CASCADE ON UPDATE CASCADE,
                                          CONSTRAINT FOREIGN KEY (partOrderId) REFERENCES vehiclePartsOrder(partOrderId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE vehicle(
                        vehicleId VARCHAR(30) PRIMARY KEY,
                        qty INT,
                        vehicleType VARCHAR(30),
                        nameOfVehicle VARCHAR(30),
                        colour VARCHAR(35),
                        price DOUBLE DEFAULT 0.00
);
CREATE TABLE vehicleOrder(
                             vehicleOrderId VARCHAR(30) PRIMARY KEY,
                             orderDate VARCHAR(15),
                             orderTime VARCHAR(15),
                             cost DOUBLE DEFAULT 0.00,
                             customerId VARCHAR(30),
                             CONSTRAINT  FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE vehicleOrderDetails (
                                     vehicleOrderId VARCHAR(30),
                                     vehicleId VARCHAR(30),
                                     qtyForSale INT,
                                     Price DOUBLE DEFAULT 0.00,
                                     CONSTRAINT FOREIGN KEY (vehicleOrderId) REFERENCES vehicleOrder(vehicleOrderId) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT FOREIGN KEY (vehicleId) REFERENCES vehicle(vehicleId) ON DELETE CASCADE ON UPDATE CASCADE
);
