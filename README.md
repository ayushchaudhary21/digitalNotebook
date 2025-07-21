
# 📝 Digital Notebook

A full-stack Java application built with **Spring Boot** that allows users to securely create, manage, update, and delete personal notes.

---

## 🚀 Features

- 🛡️ Basic Authentication with Spring Security
- 🧑‍💼 User registration & login
- 📝 CRUD operations for notes
- 🗂 Organized structure: Controller, Service, Repository
- ⚠️ Global Exception Handling
- 🔐 Password encryption using BCrypt
- 📦 RESTful API structure

---

## 🧰 Tech Stack

| Layer        | Technology            |
|--------------|------------------------|
| Backend      | Spring Boot, Spring Security |
| Database     | MySQL / H2 (Dev mode)       |
| Security     | Basic Authentication        |
| Build Tool   | Maven                      |
| Java Version | 17+ (Recommended)           |

---

## 📁 Project Structure

```
src
└── main
    └── java
        └── com.note.Notes
            ├── Controller
            ├── Service
            ├── Repository
            ├── Entity
            ├── Exceptions
            └── Configuration
```

---

## 🛠️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/digital-notebook.git
cd digital-notebook
```

### 2. Add MySQL Configuration (or use H2)

In `application.properties`:

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/notesdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```



---

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

---

### 4. Test API with Basic Auth

Use Postman or curl:

```bash
curl -u username:password http://localhost:8080/api/notes
```

---
## 🧪 API Endpoints

### 🧑 User Endpoints

| Method | Endpoint                         | Description             |
|--------|----------------------------------|-------------------------|
| POST   | `/user`                          | Register new user       |
| GET    | `/user/getbyuserName/{userName}` | Get user info by name   |
| PUT    | `/user/update/{userName}`        | Update user info        |
| DELETE | `/user/deletebyid/{id}`          | Delete user by ID       |

---

### 📝 Notes Endpoints

| Method | Endpoint                                      | Description                |
|--------|-----------------------------------------------|----------------------------|
| POST   | `/Notes/{userName}`                           | Create a note              |
| GET    | `/Notes/getAll/{userName}`                    | Get all notes for a user   |
| GET    | `/Notes/getbyid/{id}/{userName}`              | Get note by ID             |
| GET    | `/Notes/getbytitle/{title}/{userName}`        | Get note by title          |
| PUT    | `/Notes/updatebytitle/{userName}/{title}`     | Update note by title       |
| DELETE | `/Notes/deletebytitle/{title}/{userName}`     | Delete note by title       |


## ✅ To-Do (Next Steps)

- [ ] Add JWT Authentication
- [ ] Add frontend (React/Angular)
- [ ] Add reminder, pin, and tag features
- [ ] Add user sharing or collaborative notes
- [ ] Deploy to Render / Railway

---

## 📜 License

This project is licensed under the MIT License.  
Feel free to fork and enhance it!

---

## 🙌 Author

Made with ❤️ by [Ayush Chaudhary]
