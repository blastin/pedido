
# Order Project

### Order is a software development project to learn about hexagonal architecture and clean architecture. It's builder without dependencies of frameworks, because according with clean architeture, frameworks, database, protocol of communication is a details and it's not necessary in the earling of development.

#### Order have four user case to solve. 

* New Order
* Cancel Order
* Change state of order to paid
* Rollback order after non payment or something else

### More about Order

Order represents the first of three components base of project of case of study about e-commerce. Order is a software based in Event Driven Architecture then for example, after new order is effective, a event command is dispatch. In low levels of details message stream will be necessary utilizing a message brokers.
On this moment non was decided type of database for the project. How it is a low levels details of architecture, I think I will choose a simples SQL database in this point and then analyse a better proposal to later. 


## Important Concepts of clean architecture used in this project

