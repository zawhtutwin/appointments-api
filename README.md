api/auth "POST" 
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/AuthController.java
```json
{
	"email": "user.example.com",
	"password": "password"
}
```

api/doctor "GET"
https://github.com/zawhtutwin/appointments-api/blob/master/src/main/java/com/clinic/app/controllers/apis/DoctorApiController.java

Features: 
- Get the api key from api/auth "POST" 
- Use the api key from that request in subsequent request like /api/doctors "GET"
- Role Based Bearer Token Authorization(Default ROLE_USER)
