
# 🏥 Clinic App API

Simple REST API for managing doctors and appointments using Spring Boot.  
Authentication is done via API key using Bearer tokens.

---

## 🔐 Features

- ✅ Get an API key by sending a `POST` request to `/api/auth`.
- 🔐 Use the API key as a **Bearer token** in the `Authorization` header for protected routes (e.g., `/api/doctors`).
- 🔑 Role-based security (`ROLE_USER` by default).
- 🌐 SQLite as the embedded database.
- 📄 Auth controller logic: [`AuthController.java`](https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/AuthController.java)

---
## 📚 API Endpoints

| Endpoint | Method | Request Body | Sample Response |
|----------|--------|--------------|-----------------|
| `http://localhost:8081/api/auth` | POST | See  [below](#api-auth-request-body)| See [below](#api-auth-response-body) |
| `http://localhost:8081/api/doctors` | GET | None | See [below](#api-doctors-response) |

---

## 🔹 `/api/auth` 

 <a name="api-auth-request-body"></a> Api Auth Request Body
```json
{
  "email": "user@example.com",
  "password": "password"
}

```
<a name="api-auth-response-body"></a> Response
```json
{
    "apiKey": "53922079-9877-42b3-967d-4fa6c2438b97",
    "expiresAt": "2025-04-29T04:23:20.485357600Z"
}
```


---

## 🔹 `/api/doctors`  
<a name="api-doctors-response"></a>Response
```json
{
  "apiKey": "53922079-9877-42b3-967d-4fa6c2438b97",
  "expiresAt": "2025-04-29T04:23:20.485357600Z"
}
```
---

### application.properties

```properties
spring.application.name=clinic_app
server.port=8081
spring.datasource.url=jdbc:sqlite:mydatabase.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```
