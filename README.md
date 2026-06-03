# Food Ordering System

## Project Title
Food Ordering System

---

## Project Description
The Food Ordering System is a Java-based desktop application developed using Swing GUI, JDBC, and MySQL.

This application allows users to:
- View food menu
- Select food items
- Generate bills
- Store orders in database

---

## Features
- Menu Display
- Order Management
- Bill Generation
- Quantity Selection
- Payment Simulation
- Login System
- Receipt Generation

---

## Technologies Used
- Java
- Swing
- JDBC
- MySQL
- Collections Framework

---

## Software Requirements
- Java JDK
- VS Code
- MySQL Server
- MySQL Workbench
- JDBC Driver

---

## Project Structure

FoodOrderingSystem
│
├── DBConnection.java
├── FoodOrderingSystem.java
├── LoginPage.java
├── AdminPanel.java
├── README.md

---

## Database Setup

Run the following SQL commands:

```sql
CREATE DATABASE foodorder;

USE foodorder;

CREATE TABLE orders(
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(50),
    price INT
);