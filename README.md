## Online-store NIC

#### Используемые технологии:
* Java 8
* Spring 5
* JWT Token
* PostgreSQL (JPA - провайдер Hibernate)
* Docker

---
#### Запуск приложения с помощью Docker:
1. Скачать прокет
2. Изменить настройки окружения в .env
3. Запустить с помощью следующих команд:
```
docker-compose build
docker-compose up
```


#### Запуск приложения с помощью среды разработки:
1. Клонируем прокет
2. Изменяем настройки в application.properties
3. Запускаем проет

---
##### Генерация ключа шифрования

Для работы приложения нужен ключ  шифрования с минимальным уровнем шифрования 256 бит.
Этот ключ нужно ввести в переменную 'jwt_secret' в application.properties,
или/и в .env
Можете придуамать его сами или же воспользоваться любым сервисом для генерации
рандомных ключей шифрования, например, [Encryption Key Generator](https://randomgenerate.io/encryption-key-generator)