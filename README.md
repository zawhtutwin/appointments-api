
**Features:**
- Get the api key from api/auth "POST" 
- Use the api key from that request in subsequent request like /api/doctors "GET"
- Role Based Bearer Token Authorization(Default ROLE_USER)

http://localhost:8081/api/auth "POST" \
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/AuthController.java
```json
{
	"email": "user.example.com",
	"password": "password"
}
```

http://localhost:8081/api/doctor "GET"\
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/DoctorApiController.java
sample json reponse\
```json
[
    {
        "id": 1,
        "name": "Dr.Mary Moe",
        "phone": "234567"
    }
]
```

application.properties

```properties
spring.application.name=clinic_app
server.port=8081
spring.datasource.url=jdbc:sqlite:mydatabase.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

