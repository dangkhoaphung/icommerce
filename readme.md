# Readme
## About application
Application has 2 main services (product and cart) together with registry service. Ideally, application should have authentication service and use JWT to exchange user info between services. In this context, application doesn't have authentication service, so cart service will hardcoded 1 account id as an authenticated user.
In flow get cart info, cart service will call product service to get product detail. Application applied caching in the request from cart service to product service, this will help to improve performance in case 1 product is called multiple time. Application also applied circuit breaker pattern in this flow. In case one product service has issue and cannot response, circuit breaker will open and let the service have time to recover.
Distributed tracing and global exception handler are enabled in application as well
## Folder Structure
Code folder structure of all components have similar structure. Under main package com.icommerce.<component-name> (ex: com.icommerce.cart), there's a list of folders:
* config : contains all configuration classes
* controller
* entity
* exception
* handler : contains exception handler
* repository
* requestDTO : request objects will be defined in this folder
* responseDTO : response objects will be defined in this folder
* service : service layer of application
## Used Libraries
* Spring Boot
* Spring Cloud Netflix - Eureka
* Spring Cloud Config
* Spring Cloud Sleuth
* Spring Cloud Circuit Breaker
* Spring Cloud OpenFeign
* Spring Web
* Spring Data
* ehcache
## Steps to run
Prequisite
* JDK 8 or higher
* Maven

Steps
1. Pull source code
2. Go to icommerce folder (contains start.sh and stop.sh inside)
3. Run shell script "./start.sh" to build and start services by just one run
4. Waiting for services to start
5. Use curl command to verify
6. Run "./stop.sh" to stop all services

## CURL
* To get all products (init 8 products in DB already)
curl http://localhost:6001/product/get-all

* To add product to cart (get product id from above command)
curl -d '{"productId":1, "quantity": 2}' -H "Content-Type: application/json" -X POST http://localhost:6011/cart/add
curl -d '{"productId":2, "quantity": 4}' -H "Content-Type: application/json" -X POST http://localhost:6011/cart/add

* To get cart info
curl http://localhost:6011/cart/get
