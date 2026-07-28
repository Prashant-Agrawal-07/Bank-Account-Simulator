# Bank-Account-Simulator

# 🏦 Bank Account Simulator

A simple **Java console-based banking application** that demonstrates the principles of **Object-Oriented Programming (OOP)**, including inheritance, polymorphism, abstraction, and exception handling. The application allows users to create and manage different types of bank accounts through a menu-driven interface.

---

## 🚀 Features

- Open new bank accounts
- Deposit money
- Withdraw money
- Transfer money between accounts
- View all account details
- Save account data before exiting
- Custom exception handling
- Menu-driven console interface
- Object-Oriented Programming (OOP) implementation

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Exception Handling
- Collections
- Console I/O (Scanner)

---

## 📂 Project Structure

```
BankAccountSimulator/
│
├── Account.java
├── SavingsAccount.java
├── CurrentAccount.java
├── Bank.java
├── BankApp.java
├── InvalidAmountException.java
├── InsufficientBalanceException.java
└── README.md
```

---

## 📖 OOP Concepts Used

- **Abstraction** – Common account behavior defined in the base class.
- **Inheritance** – SavingsAccount and CurrentAccount inherit from Account.
- **Polymorphism** – Operations performed through parent class references.
- **Encapsulation** – Account details are protected using private/protected members.
- **Exception Handling** – Custom exceptions for invalid transactions.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Prashant-Agrawal-07/Bank-Account-Simulator.git
```

### 2. Navigate to the project folder

```bash
cd BankAccountSimulator
```

### 3. Compile the project

```bash
javac *.java
```

### 4. Run the application

```bash
java BankApp
```

---

## 📋 Sample Menu

```
========== Bank Account Simulator ==========

1. Open New Account
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. View All Accounts
6. Save & Exit

Enter choice:
```

---

## ⚠️ Exception Handling

The project includes custom exceptions such as:

- `InvalidAmountException`
  - Thrown when the deposit or withdrawal amount is invalid.

- `InsufficientBalanceException`
  - Thrown when attempting to withdraw more than the available balance.

---

## 🎯 Learning Objectives

This project helps in understanding:

- Java Classes & Objects
- Inheritance
- Method Overriding
- Polymorphism
- Constructors
- Encapsulation
- Exception Handling
- Menu-driven programming
- Console-based application development

---

## 🌟 Future Enhancements

- Store account data in files
- Database integration (MySQL)
- Login authentication
- Transaction history
- Interest calculation for savings accounts
- GUI using Java Swing or JavaFX
- REST API using Spring Boot

---

## 👨‍💻 Author

**Prashant Agrawal**

GitHub: https://github.com/Prashant-Agrawal-07

---