# Spring Security OAuth2

Spring security OAuth2 demo

Example:

`curl --location --request POST 'http://localhost:8081/demo/oauth/token' \
 --header 'Authorization: Basic ZGVtby1pZDpkZW1vLXNlY3JldA==' \
 --form 'grant_type=password' \
 --form 'username=admin' \
 --form 'password=admin'
`