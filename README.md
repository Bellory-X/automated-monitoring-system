# automated-monitoring-system
Приложение по мониторингу веб сайтов
На первом слайде изображена главная страница
Здесь отображаюися 4 списка: удаленые страницы, редактированные, созданные и не модифицируемые относительно прошлого дня.
Отправка сообщения секретарю происходит двумя способами: 
1) по нажатию соответсвующей кнопки на главной странице
2) в конце дня письмо отправится автоматически и происходит архивация страниц,
   тоесть  замена сегоднешних веб страниц в вчерашнее с удалением бывших вчерашних 
![Screenshot 2024-06-06 191317](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/13cd2208-cea0-46f0-a49e-b3ef20783727)
![Screenshot 2024-06-06 190906](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/6afd3e52-ade4-4249-aed9-6ea88edb8419)
![Screenshot 2024-06-06 191838](https://github.com/Bellory-X/automated-monitoring-system/assets/80157339/fbcd7d9d-d973-4b0f-a2a9-7abbcce598da)

Запуск приложения состоит из 3 шагов:
1) в файле compose.yaml необходимо прописать в свои зависимости для интеграции с почтой
2) сборка проека проекта
3) запуск докер оброза

Сам проект состоит из 3 дерикторий: 
1) platform - Различные вспомогательные функции и класс эмуляции базы данных, в котором происходит обработка двух HashMap из условия задания
2) core - Реализация бизнес-логики по обработки удаления и редактирования веб страниц
3) app - Реализованы адапторы и модели по анализу и чтения веб страниц
