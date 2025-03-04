# Описание приложения "Weather Tracker"
Weather Tracker - это веб-приложение,
которое позволяет пользователям получать актуальную информацию о погоде и прогноз погоды для выбранного города.
Приложение предоставляет удобный интерфейс для поиска и отображения данных о текущей погоде и прогнозе на ближайшие дни.
# Основные функции
1. Поиск погоды по городу:
Пользователь может ввести название города в поисковую строку и получить актуальную информацию о погоде в этом городе.
Отображаемая информация включает температуру, ощущаемую температуру, описание погодных условий, влажность, давление, скорость и направление ветра, облачность, количество осадков (дождь и снег), время восхода и заката солнца.
2. Прогноз погоды:
Пользователь может получить прогноз погоды на ближайшие 5 дней с шагом в 3 часа.
Прогноз включает температуру, ощущаемую температуру, описание погодных условий, влажность, давление, скорость и направление ветра, облачность, количество осадков (дождь и снег).
3. Удобный интерфейс:
Приложение имеет простой и интуитивно понятный интерфейс, выполненный с использованием Bootstrap.
Результаты поиска и прогноз погоды отображаются в виде карточек с анимацией появления.
4. Кнопка очистки:
Пользователь может очистить поле ввода города с помощью кнопки очистки.
#   Swagger
Приложение включает документацию API с использованием Swagger. Вы можете получить доступ к Swagger UI по следующему URL: http://localhost:8080/swagger-ui/index.html

# Технологии
#### Backend:
Java 17 (Amazon Corretto 17.0.10)
Spring Boot,
Spring Data JPA,
Hibernate,
REST API
#### Frontend:
HTML5,
CSS3,
JavaScript (jQuery),
Bootstrap 5
#### Интеграции:
OpenWeatherMap API для получения данных о погоде и прогнозе.

# Установка и запуск
#### Клонирование репозитория:
git clone https://github.com/Trebolll/my-app.git,
cd my-app
####  Сборка и запуск приложения:
./mvnw clean install
./mvnw spring-boot:run
####  Открытие в браузере:
Перейдите по адресу http://localhost:8080 в вашем браузере.

# my-app-starter
Maven-проект нашего стартера. Сборка и установка артефакта в локальный maven-репозиторий происходит с помощью команды:
`mvn clean install`.

# my-app
Maven-проект клиентского приложения, которое демонстрирует использование стартера из проекта my-app-starter.
# Контакты
##### Автор: morev
##### Проект на GitHub: https://github.com/Trebolll/my-app