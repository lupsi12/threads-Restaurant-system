# Restaurant Management System Simulation

Using Java and Swing management processes, this project simulates the routine operation of a restaurant. In the project, restaurant employees such as customers, waiters, cooks and cashiers were simulated with multi-threading technology. Customers enter the restaurant according to their scenario and are divided into elective and regular customers. Waiters take orders and pass them on to the cooks; chefs cook the meals and deliver some. After the customers eat their meals, they pay the bill and leave the restaurant. The application starts by personally asking which problem you want to solve and the simulation is started according to the scenario. Threads are loaded with a step forward semaphore. Each thread proceeds with its own type of activities and state. For example, customer threads follow steps such as getting seated, ordering, waiting for the food, and paying. Waiter threads take customer orders and deliver them to the cooks. The cooks cook the orders. Case threads receive their payments in their country and terminate customer threads. Some problems were encountered while developing the project, especially the implementation of the cooks' cooking process and the spread over of threading problems. These issues have been resolved by debugging and fixing updates. Restaurant Management System provides important features of multi-threading and information.

## Project Overview

The Restaurant Management System simulates the routine operation cycle of a restaurant. Various roles, including customers, waiters, chefs, and cashiers, are simulated using different threads. Customers enter the restaurant, place orders, eat, and pay before leaving. Waiters take orders and deliver them to chefs. Chefs prepare the meals, which are then served to customers by the waiters. The cashier handles the payments from customers.

## Project Pictures

![Restaurant Management System](https://github.com/lupsi12/threads-Restaurant-system/assets/105547899/af74ddd1-2f3b-429c-8582-58e788bfb6cf)

## Setup

### Requirements

- Java Development Kit (JDK) 8 or above
- Apache Maven or Gradle (optional but recommended)
- An IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.)

### Steps

1. **Download the Source Code:**
   ```sh
   git clone [https://github.com/username/restaurant-management-system.git](https://github.com/lupsi12/threads-Restaurant-system.git)
  
