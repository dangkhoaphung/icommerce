#!/bin/bash

mvn clean install
echo "Start Registry service..."
exec -a registry java -Dlogging.dir=./logs -jar registry-service/target/registry-service-1.0.0-RELEASE.jar >/dev/null 2>&1 &
sleep 10s;
echo "Start Product service..."
exec -a product java -Dlogging.dir=./logs -jar product-service/target/product-service-1.0.0-RELEASE.jar >/dev/null 2>&1 &
echo "Start Cart service..."
exec -a cart java -Dlogging.dir=./logs -jar cart-service/target/cart-service-1.0.0-RELEASE.jar >/dev/null 2>&1 &
sleep 15s;
echo "Started all services. Please go to this url http://localhost:8761/registry/ to verify. If Produc/Cart service is not available, please wait for a moment and try again"