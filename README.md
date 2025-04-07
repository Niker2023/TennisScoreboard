Tennis Match Simulator

Веб-приложение для симуляции и отслеживания теннисных матчей. Приложение позволяет:

- Создавать новые теннисные матчи между двумя игроками
    
- Отслеживать ход текущего матча с отображением счета по сетам и геймам
    
- Просматривать историю завершенных матчей
    
- Искать матчи по имени игрока
    

## Технологии

- **Backend**: Java 17, Jakarta EE (Servlets, JSP)
    
- **Database**: Hibernate ORM, H2 Database (или другая реляционная БД)
    
- **Frontend**: HTML, CSS, JSP
    
- **Build Tool**: Maven
    
- **Testing**: JUnit 5
    

## Функциональность

### Основные функции

1. **Главная страница**:
    
    - Создание нового матча
        
    - Просмотр завершенных матчей
        
2. **Создание матча**:
    
    - Ввод имен игроков
        
    - Валидация вводимых данных
        
3. **Текущий матч**:
    
    - Обновление счета при выигрыше очка
        
    - Отображение счета по сетам и геймам
        
    - Определение победителя матча
        
4. **Завершенные матчи**:
    
    - Просмотр списка матчей с пагинацией
        
    - Поиск по имени игрока
        
    - Подсветка победителя в каждом матче
        

## Установка и запуск
   
- git clone https://github.com/Niker2023/TennisScoreboard
- docker build -t app .
- docker run -p8080:8080 app

## Структура проекта
<pre>
src/
├── main/ 
│   ├── java/
│   │   ├── com/project/
│   │   │   ├── controller/      # Сервлеты
│   │   │   ├── service/         # Бизнес-логика
│   │   │   ├── repository/      # DAO классы
│   │   │   ├── entity/          # Сущности БД
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── exception/       # Пользовательские исключения
│   │   │   └── util/            # Вспомогательные классы
│   ├── resources/               # Конфигурационные файлы
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── jsp/             # JSP страницы
│       │   └── web.xml          # Конфигурация веб-приложения
│       ├── css/                 # Стили
│       └── images/              # Изображения
└── test/                        # Тесты
</pre>

## Скриншоты
<details>
<summary>Развернуть</summary>

![ts_home](https://github.com/user-attachments/assets/c09e19b5-1f7e-4e42-900a-1105b9656096)
![ts_newMatch](https://github.com/user-attachments/assets/fac69331-bbf6-4c54-8557-8b0169565953)
![ts_ongoingMatch](https://github.com/user-attachments/assets/77ad1a89-cc26-4550-b92e-8014cdd49e6a)
![ts_finishedMathces](https://github.com/user-attachments/assets/c4a6eaca-4da4-43f1-9415-191b9f049ad4)

</details>