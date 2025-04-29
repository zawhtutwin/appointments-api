
**Features:**
- Get the api key from api/auth "POST" 
- Use the api key from that request in subsequent request like /api/doctors "GET"
- Role Based Bearer Token Authorization(Default ROLE_USER)

http://localhost:8081/api/auth "POST" \
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/AuthController.java


| url                             | method | default request body                                                                | Sample response                                                                                                        |
| ------------------------------- | ------ | ----------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| http:localhost:8081/api/auth    | POST   | {

"email":"[user@example.com](mailto:user@example.com)",

"password":"password"

} |
{

        "apiKey": "53922079-9877-42b3-967d-4fa6c2438b97",

        "expiresAt": "2025-04-29T04:23:20.485357600Z"

}
 |
| http:localhost:8081/api/doctors | GET    |                                                                                     | [


  {

       "id": 1,

        "name": "Dr.Mary Moe",

        "phone": "234567"

  }

]                             |

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

