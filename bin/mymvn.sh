cd ..

cd dependencies
call mvn clean install
cd..

cd commons
call mvn clean install
cd..

cd domain
call mvn clean install
cd..

cd redis-service-api
call mvn clean install
cd..

cd order-service-api
call mvn clean install
cd..

cd user-service-api
call mvn clean install
cd..

cd payment-service-api
call mvn clean install
cd..

cd channel-service-api
call mvn clean install
cd..

