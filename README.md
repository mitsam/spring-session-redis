# Description
Its a demo project to illustrate spring session with redis. Sometimes in some cases it can be a good idea to use session for storing user data in rest APIs too. 

# Working
Here, I have used spring security's default login and logout mechanism to illustrate spring session with redis. You can use cURL command to check the functionality.

## Login
This login API will write X-Auth-Token as response header. And this is the key to session management in spring session.

aks@SDL6726:~$ curl -X POST -i -F 'username=amit' -F 'password=samuel' http://localhost:8080/login

HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
X-Auth-Token: c4e81961-2abe-471f-b8cf-79a23d7ac9dc
Content-Length: 31
Date: Fri, 16 Aug 2019 07:23:13 GMT

"Login successful with... amit"

## API used to store session attribute in session 
After successful login, we have to provide X-Auth-Token as a request header in all subsequent requests, so that session can be maintained. 

aks@SDL6726:~$ curl -X GET -i -H "X-Auth-Token: c4e81961-2abe-471f-b8cf-79a23d7ac9dc" http://localhost:8080/api/resource1

HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 16 Aug 2019 07:23:48 GMT

{"resource":"here is some resource"}

## API used to get stored session attribute from session
aks@SDL6726:~$ curl -X GET -i -H "X-Auth-Token: c4e81961-2abe-471f-b8cf-79a23d7ac9dc" http://localhost:8080/api/resource2

HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 16 Aug 2019 07:24:07 GMT

{"resource":"here is some resource"}

