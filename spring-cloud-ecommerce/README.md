# Install

## Import Postman Collection

Import the following two files into Postman

- postman/postman_collection.json
- postman/postman_environment.json

![image](https://user-images.githubusercontent.com/71188307/147405724-85183c0e-4e66-4fa7-b491-19fa9f5f126e.png)

## Clone Project

```shell
mkdir dirName
cd dirName
git init
git config core.sparseCheckout true
git remote add -f origin https://github.com/shirohoo/spring-cloud-examples.git
echo spring-cloud-ecommerce/ >> .git/info/sparse-checkout
git pull origin main
```

## Install RabbitMQ

```shell
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:management
```

## Run Applications

![image](https://user-images.githubusercontent.com/71188307/147405655-cebc2197-17bc-4199-b799-5a141b6d53db.png)

