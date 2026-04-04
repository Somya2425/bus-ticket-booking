# 🚌 Bus Ticket Booking System

## 📌 Overview

The **Bus Ticket Booking System** is a backend application built using Spring Boot to manage bus travel operations for agencies and customers.

---

## 🎯 Outcome

This system enables:

* Retrieval of offices, buses, drivers, and trips
* Searching trips between source and destination
* Viewing available seats for trips
* Accessing route insights like popularity

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot
* **Language:** Java
* **Database:** MySQL
* **ORM:** Hibernate / JPA
* **Mapping:** ModelMapper
* **Build Tool:** Maven

---

## ⚙️ Configuration

The application uses **`application.yaml`** for configuration.

### 🔌 Server Configuration

```yaml
server:
  port: 8082
```

### 🗄️ Database Configuration (Example)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bus_booking
    username: root
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 📡 API Endpoints

| Feature                        | Endpoint                                                      | Tables Used               |
| ------------------------------ | ------------------------------------------------------------- | ------------------------- |
| Get customer by name & address | `/api/customer/{name}/{address}`                              | Customers, Addresses      |
| Get customer bookings          | `/api/customer/bookings`                                      | Customers, Payments       |
| Get customers of an agency     | `/api/agency/{agencyId}/customers`                            | Customers, Agency         |
| Get all offices of an agency   | `/api/agency/{agencyId}/offices`                              | Agency, Agency_Offices    |
| Get buses by agency & date     | `/api/agency/{agencyId}/buses/{date}`                         | Agency, Buses (via Trips) |
| Get buses of an office         | `/api/office/{officeId}/buses`                                | Agency_Offices, Buses     |
| Get revenue per agency         | `/api/agency/{agencyId}/revenue`                              | Agency, Payments          |
| Get drivers of an office       | `/api/offices/{officeId}/drivers`                             | Agency_Offices, Drivers   |
| Search trips                   | `/api/trips/search?source={source}&destination={destination}` | Trips, Addresses          |
| Get available seats            | `/api/trips/seats?tripId={tripId}`                            | Trips, Bookings           |
| Search routes                  | `/api/route/search?source={source}&dest={dest}`               | Routes                    |
| Popular routes                 | `/api/route/popular?limit={limit}`                            | Routes, Trips             |
| Traffic insights               | `/api/route/traffic`                                          | Routes, Trips             |
| Successful bookings            | `/api/customer/booking/status/success`                        | Customers, Bookings       |

---

## 🔐 Features

* Input validation using annotations (`@NotNull`, `@NotBlank`)
* Centralized exception handling
* Standardized API responses
* Logging using SLF4J
* Clean layered architecture

---

## 📂 Project Structure

```
com.cg.busbooking
│── controller        # REST Controllers
│── service           # Interfaces
│── service.impl      # Business logic implementation
│── repository        # JPA Repositories
│── entity            # Database entities
│── dto               # Request/Response DTOs
│── constants         # Constants
│── exception         # Custom exceptions
```

---

## ▶️ How to Run

1. Clone the repository
2. Configure MySQL in `application.yaml`
3. Run the application:

   ```
   mvn spring-boot:run
   ```
4. Access APIs:

   ```
   http://localhost:8082
   ```

---

## 🧪 Testing

* Use **Postman** to test APIs
* Pass query parameters and path variables correctly

---

## 🚀 Future Enhancements

* Add authentication & authorization (JWT)
* Add payment integration
* Add Swagger/OpenAPI documentation
* Implement real-time seat updates


---

## 📄 License

This project is for educational purposes.
