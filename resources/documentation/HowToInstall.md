# Как настроить проект?

## Необходимое ПО

Для корректной работы с проектом вам понадобятся следующие программы:
- IDEA Ultimate edition
- PostgreSQL
- pgAdmin 4 (обычно она устанавливается вместе с PostgreSQL)
- Oracle OpenJDK version 17 (можете установить через IDEA, если ещё нет)

## Установка

### 1. Ипорт базы данных

1. backup БД лежит по адресу:
 *resources/database-backup/ecomoney-schema.dmp*
2. Схему необходимо импортировать в БД postgres (бд по умолчанию, создается при установки PostgreSQL)
3. Для импорта удобно использывать pgAdmin 4

Видео, демонстрирующий процедуру импорта:

[![Watch the video](https://i9.ytimg.com/vi_webp/XrmquBoZPyI/mqdefault.webp?v=62799cd9&sqp=CPC55pMG&rs=AOn4CLD2sNHm4U9V2qJtQyl18FIQwW5GWA)](https://youtu.be/XrmquBoZPyI)

### 2. Установка сертификатов ssl

Для доступа к api проекте используется протокол https.
Для запуска проекта на ПК используются самоподписанные SSL сертификаты, их неообходимо установить.
Сами сертификаты лежат в каталоге: *resources/ssl*

- rootca.cer - сертификат удостоверяющего центра
- ecomoney_ssl_rootca_.cer

Видео, демонстрирующее процедуру установки сертификатов:

[![Watch the video](https://i9.ytimg.com/vi/cXcEs7tq_bc/mq2.jpg?sqp=CPzO5pMG&rs=AOn4CLDFNkfUXnyfUdjbMW8eR5opQGHV_Q)](https://youtu.be/cXcEs7tq_bc)

### 3. Запуск проекта

Запуск ничем не отличается от запуска других Java проектов, запуск рекомендуется осуществлять через IDEA Ultimate Edition



