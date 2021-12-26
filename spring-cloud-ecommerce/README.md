# Install

## Clone project

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
