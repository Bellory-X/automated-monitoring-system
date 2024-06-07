# automated-monitoring-system
Приложение по мониторингу веб сайтов
На первом слайде изображена главная страница
Здесь отображаюися 4 списка: удаленые страницы, редактированные, созданные и не модифицируемые относительно прошлого дня.
Отправка сообщения секретарю происходит двумя способами: 
1) по нажатию соответсвующей кнопки на главной странице
2) в конце дня письмо отправится автоматически
![Screenshot 2024-06-06 191317](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/13cd2208-cea0-46f0-a49e-b3ef20783727)
![Screenshot 2024-06-06 190906](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/6afd3e52-ade4-4249-aed9-6ea88edb8419)
![Screenshot 2024-06-06 191838](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/fbcd7d9d-d973-4b0f-a2a9-7abbcce598da)

Запуск приложения состоит из 3 шагов:
1) в файле compose.yaml необходимо прописать в свои зависимости для интеграции с почтой
2) сборка проека проекта
3) запуск докер оброза

Ключевое отличие от предыдущей версии изменение архитектуры приложения:
1) Вырезана CQRS c разделением на модели на для чтения и записи. То есть объеденины сервисы и репозитории
2) Вырезана доменная модель с агренгатом и объектами значений
   
В связи с этим убрана пакет core, а app теперь состоит из api с интерфейсами и dto, impl с реализацией сервисов и других внутренних классов включая класс эмуляции базы данных и пакета port с контроллером.
