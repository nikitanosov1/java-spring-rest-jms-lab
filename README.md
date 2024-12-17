# java-spring-rest-jms-lab
Лабораторные работы #1/2/3 по курсу "Архитектура корпоративных систем"

## О сервисе
Сервис предоставляет возможность создавать владельцев автомобителей и сами автомобили. Владельны связаны с авто как один ко многим. Также имеется возможность удалять владельцев и авто, а также обновлять их. При удалении владельца также удаляются все его автомобили.

## Студенты, сделавшие проект
6132 Лаптев Владислав

6132 Носов Никита

# Используемые сущности

В проекте используются две сущности: Car и Owner

Car содержит id (УИД), brand (бренд машины), modelName (название модели)

Owner содержит id (УИД), firstName (имя), lastName (фамилия), birthdate (дата рождения)

EmailNotification содержит id (УИД), address (адрес), content (описание)

Logging содержит id (УИД), entity (название класса Entity), eventType (CREATE/DELETE/UPDATE), substance (аргументы)


Диаграмма БД выглядит следующим образом:

![image](https://github.com/user-attachments/assets/763cabc7-6f8e-4859-a746-150471f77c70)

# Демо работы

![image](https://github.com/user-attachments/assets/9f4183ab-70c5-48e6-8038-fd0568600a1e)

![image](https://github.com/user-attachments/assets/19f083fc-7dfc-48ea-afd2-5594bb50c156)

![image](https://github.com/user-attachments/assets/abf7da49-3e91-4d43-984c-d58ce8ec0c73)

![image](https://github.com/user-attachments/assets/22a3cf14-0477-407b-9d65-89fa383717f1)

![image](https://github.com/user-attachments/assets/fec5970d-92a5-40d5-955d-3cc28d0cd362)

![image](https://github.com/user-attachments/assets/b89bebde-3a68-490f-80da-eb0b4557a5e9)

![image](https://github.com/user-attachments/assets/240539be-8b8e-4399-98d6-819d7e81f009)

![image](https://github.com/user-attachments/assets/dc27c58b-a871-4b39-9da8-0b5b6907e92a)

![image](https://github.com/user-attachments/assets/6fea1be9-f76b-4807-a69a-9e391d0d45cb)

![image](https://github.com/user-attachments/assets/6772149f-f75a-4857-8dbd-6b84d79fa481)

![image](https://github.com/user-attachments/assets/5271cb6e-585a-4b0f-8940-78aea9b0ba68)

