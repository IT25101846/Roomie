
# Roomie 🏨
### Hotel Room Booking System

> **SE1020 – Object Oriented Programming | SLIIT Year 01, Semester 02**
> Group 4 | 2025

---

## 📖 About The Project

Roomie is a full-stack web-based hotel room booking system built using Java and Spring Boot. The system allows guests to register, browse available rooms, make reservations, process payments, and leave reviews. Hotel administrators can manage rooms, view all bookings, and oversee the entire system through a dedicated admin dashboard.

This project was developed as the SE1020 Object Oriented Programming assignment, demonstrating real-world application of core OOP concepts including Encapsulation, Inheritance, Polymorphism, Abstraction, and Information Hiding — all implemented through a layered MVC architecture with file-based data storage.

---

## ✨ Features

### Guest / Customer
- Register a new account and log in securely
- Browse all available rooms with details and amenities
- Book a room by selecting dates — price calculated automatically
- View, edit, and cancel personal reservations
- Process payments via Cash, Card, or Online
- Submit, edit, and delete room reviews

### Admin
- Secure admin login with role-based access
- Dashboard showing live stats — rooms, customers, reservations, payments
- Add, edit, and delete rooms
- View and delete customer accounts
- View all reservations and payment records
- Manage admin accounts

---

## 🛠️ Built With

|      Technology      |               Purpose                 |
|----------------------|---------------------------------------|
| Java 25              | Core programming language             |
| Spring Boot 4.0.3    | Web framework and embedded server     |
| Spring MVC           | Controller layer and request handling |
| JSP + JSTL           | View layer / frontend templates       |
| Bootstrap 5          | UI styling and responsiveness         |
| Maven                | Build and dependency management       |
| File I/O (TXT files) | Data storage — no database required   |
| JUnit 5              | Unit testing                          |
| GitHub               |  Version control and collaboration    |

---

## 🎓 OOP Concepts Demonstrated
        
|        Concept         |                                                  Where Applied                                                          |
|------------------------|-------------------------------------------------------------------------------------------------------------------------|
| **Encapsulation**      | All model fields are `private` with public getters and setters                                                          |
| **Inheritance**        | `Customer` and `Admin` extend abstract `Person` class. `Room`, `Reservation`, `Payment`, `Review` extend `BaseEntity`   |
| **Polymorphism**       | `getRole()` is overridden in `Customer` (returns `"CUSTOMER"`) and `Admin` (returns `"ADMIN"`) — dispatched at runtime  |
| **Abstraction**        | `Person` and `BaseEntity` are abstract classes. `Storable<T>` is a generic interface defining the repository contract   |
| **Information Hiding** | Private fields in all model classes are only accessible through controlled public methods                               |

---

## 📁 Project Structure

```
Roomie/
├── data/
│   ├── customers.txt
│   ├── rooms.txt
│   ├── admins.txt
│   ├── reservations.txt
│   ├── payments.txt
│   └── reviews.txt
└── src/
    ├── main/
    │   ├── java/mtr/group4/Roomie/
    │   │   ├── model/
    │   │   │   ├── Person.java           (abstract)
    │   │   │   ├── BaseEntity.java       (abstract)
    │   │   │   ├── Customer.java
    │   │   │   ├── Admin.java
    │   │   │   ├── Room.java
    │   │   │   ├── Reservation.java
    │   │   │   ├── Review.java
    │   │   │   └── Payment.java
    │   │   ├── exception/
    │   │   │   ├── RoomieException.java
    │   │   │   ├── AuthenticationException.java
    │   │   │   ├── DuplicateEmailException.java
    │   │   │   ├── EntityNotFoundException.java
    │   │   │   └── RoomNotAvailableException.java
    │   │   ├── repository/
    │   │   │   ├── Storable.java         (interface)
    │   │   │   ├── CustomerRepository.java
    │   │   │   ├── AdminRepository.java
    │   │   │   ├── RoomRepository.java
    │   │   │   ├── ReservationRepository.java
    │   │   │   ├── ReviewRepository.java
    │   │   │   └── PaymentRepository.java
    │   │   ├── service/
    │   │   │   ├── CustomerService.java
    │   │   │   ├── AdminService.java
    │   │   │   ├── RoomService.java
    │   │   │   ├── ReservationService.java
    │   │   │   ├── ReviewService.java
    │   │   │   └── PaymentService.java
    │   │   └── controller/
    │   │       ├── HomeController.java
    │   │       ├── CustomerController.java
    │   │       ├── AdminController.java
    │   │       ├── RoomController.java
    │   │       ├── ReservationController.java
    │   │       ├── ReviewController.java
    │   │       └── PaymentController.java
    │   ├── resources/
    │   │   └── application.properties
    │   └── webapp/WEB-INF/views/
    │       ├── navbar.jsp
    │       ├── home.jsp
    │       ├── customer/
    │       ├── room/
    │       ├── reservation/
    │       ├── payment/
    │       ├── review/
    │       └── admin/
    └── test/java/mtr/group4/Roomie/
        ├── CustomerTest.java
        ├── RoomTest.java
        ├── ReservationTest.java
        ├── AdminTest.java
        ├── ReviewTest.java
        └── PaymentTest.java
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

- Java 25
- Maven 3.8+
- IntelliJ IDEA (recommended)
- Git

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/Roomie.git
```

**2. Open in IntelliJ IDEA**
```
File → Open → Select the Roomie folder
Wait for Maven to download all dependencies
```

**3. Run the application**
```bash
mvn spring-boot:run
```

**4. Open in your browser**
```
http://localhost:8080
```

---

## 🔑 Default Login Credentials

### Admin Account
|   Field  |      Value      |
|----------|-----------------|
| Email    | admin@roomie.lk |
| Password | admin123        |

### Sample Customer Accounts
|      Email      | Password |
|-----------------|----------|
| alice@email.com | pass123  |
| bob@email.com   | pass123  |

---

## 🗃️ Data Storage

This project uses **plain text files** for data storage instead of a database. Each entity has its own `.txt` file stored in the `data/` folder at the root of the project. Records are stored in CSV format — comma separated values — one record per line.

| File | Stores |
|------|--------|
| `customers.txt` | All registered customer accounts |
| `rooms.txt` | All hotel rooms and their details |
| `admins.txt` | Admin accounts — auto-created on first run |
| `reservations.txt` | All booking records |
| `payments.txt` | All payment transactions |
| `reviews.txt` | All guest reviews |

---

## 🧪 Running Tests

```bash
mvn test
```

Each team member wrote unit tests for their own component. All tests verify model construction, CSV serialisation, OOP concepts, and edge case handling.

|      Test Class        |   Member   |Tests|
|------------------------|------------|-----|
| `CustomerTest.java`    | Lumalka    |  8  |
| `RoomTest.java`        | Liyanagama |  8  |
| `ReservationTest.java` | Deshan     |  8  |
| `AdminTest.java`       | Sandarangi |  8  | 
| `ReviewTest.java`      | Hansanee   |  6  |
| `PaymentTest.java`     | Pabodhi    |  7  |

---

## 👥 Team Members & Contributions

|           Member          |           Component          |                         Responsibilities                          |
|---------------------------|------------------------------|-------------------------------------------------------------------|
| **IT25101896-Lumalka**    | Guest Management             | Customer model, registration, login, profile management           |
| **IT25101655-Liyanagama** | Room Management              | Room model, room CRUD operations, availability tracking           |
| **IT25101846-Deshan**     | Reservation & Booking        | Reservation model, booking system, cancellation, date calculation |
| **IT25101671-Sandarangi** | Admin & Staff Management     | Admin model, admin dashboard, staff account management            |
| **IT25101494-Hansanee**   | Feedback & Review Management | Review model, review submission, editing, average rating          |
| **IT25101596-Pabodhi**    | Billing & Payment Management | Payment model, payment processing, receipts, billing records      |

---

## 📋 Component Breakdown

### Component 01 — Guest Management (Lumalka)
Handles all customer-related operations. Customers can register new accounts, log in securely, update their profile information, and delete their account. The `Customer` class extends the abstract `Person` class demonstrating inheritance.

### Component 02 — Room Management (Liyanagama)
Manages the hotel room catalogue. Admins can add new rooms, edit room details, update availability status, and delete rooms. The `Room` class extends `BaseEntity` and uses `ArrayList<String>` to store amenities dynamically.

### Component 03 — Reservation & Booking Management (Deshan)
Handles the core booking functionality. Customers can book available rooms by selecting check-in and check-out dates. The system automatically calculates the total price based on the number of nights. Bookings can be edited or cancelled, and room availability is updated automatically.

### Component 04 — Admin & Staff Management (Sandarangi)
Controls administrative access and system oversight. Admins can log in to a secure dashboard, view system statistics, manage other admin accounts, and oversee all customer records. The `Admin` class extends `Person` and supports two admin levels.

### Component 05 — Feedback & Review Management (Hansanee)
Allows customers to share their experiences. Customers can submit star ratings and written reviews for rooms they have stayed in. Reviews can be edited or deleted by the reviewer or by admins. The system also calculates average ratings per room.

### Component 06 — Billing & Payment Management (Pabodhi)
Processes payments for confirmed reservations. Customers can pay using Cash, Card, or Online methods. A payment receipt is generated after each successful transaction. Admins can view all payment records and remove entries if needed.

---

## 🔗 Application URLs

|          URL            |              Page              |  Access  |
|-------------------------|--------------------------------|----------|
| `/`                     | Home page with available rooms | Public   |
| `/customer/register`    | Customer registration          | Public   |
| `/customer/login`       | Customer login                 | Public   |
| `/customer/profile`     | Edit profile                   | Customer |
| `/room/list`            | All rooms                      | Public   |
| `/room/detail/{id}`     | Room details                   | Public   |
| `/reservation/book`     | Book a room                    | Customer |
| `/reservation/my`       | My bookings                    | Customer |
| `/payment/form/{id}`    | Payment page                   | Customer |
| `/payment/receipt/{id}` | Payment receipt                | Customer |
| `/review/all`           | All reviews                    | Public   |
| `/review/submit`        | Write a review                 | Customer |
| `/admin/login`          | Admin login                    | Public   |
| `/admin/dashboard`      | Admin dashboard                | Admin    |
| `/room/add`             | Add new room                   | Admin    |
| `/customer/list`        | All customers                  | Admin    |
| `/reservation/all`      | All reservations               | Admin    |
| `/payment/all`          | All payments                   | Admin    |
| `/admin/list`           | All admins                     | Admin    |

---

## 📝 License

This project was developed for educational purposes as part of the SE1020 Object Oriented Programming module at the Sri Lanka Institute of Information Technology (SLIIT).

---

## 🏫 Acknowledgements

- **Sri Lanka Institute of Information Technology (SLIIT)**
- **SE1020 — Object Oriented Programming Module**
- **Faculty of Computing**
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Bootstrap 5](https://getbootstrap.com)
- [Bootstrap Icons](https://icons.getbootstrap.com)
