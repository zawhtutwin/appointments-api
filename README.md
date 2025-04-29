
**Features:**
- Get the api key from api/auth "POST" 
- Use the api key from that request in subsequent request like /api/doctors "GET"
- Role Based Bearer Token Authorization(Default ROLE_USER)

http://localhost:8081/api/auth "POST" \
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/AuthController.java


# 📚 API Endpoints Documentation

| URL | Method | Default Request Body | Sample Response |
| --- | ------ | -------------------- | --------------- |
| `http://localhost:8081/api/auth` | POST | See [Request Body](#api-auth-request-body) | See [Response](#api-auth-sample-response) |
| `http://localhost:8081/api/doctors` | GET | *(none)* | See [Response](#api-doctors-sample-response) |

---

## 🔹 `/api/auth` Request Body

```json
{
  "email": "user@example.com",
  "password": "password"
}

```

---

## 🔹 `/api/doctors` Sample REsponse

{
  "apiKey": "53922079-9877-42b3-967d-4fa6c2438b97",
  "expiresAt": "2025-04-29T04:23:20.485357600Z"
}

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

