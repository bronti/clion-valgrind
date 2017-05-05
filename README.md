# CLion Plug-in: valgrind memory analyzer

## [Альтернативная версия](https://github.com/YorovSobir/clion-valgrind)

## Зависимости
1. Последняя версия Intellij IDEA (2017.1.2) а также CLion (2017.1)

## Как собрать проект
1. Открыть проект с помощью Intellij IDEA.
2. Проверить, что в качестве sdk проекта указана Inteellij Platform Plugin SDK со ссылкой на установочную папку CLion.
3. Запустить проект с конфигурацией **Plugin** (запустится инстанс CLion'а с подключенным плагином clion-valgrind).

## Запуск valgrind в CLion
1. Открыть проект, который хотим проверить на ошибки при работе с памятью.
2. Собрать проект (Build)
3. Открыть **Run -> Edit Configuration**
4. Добавить конфигурацию **valgrind**.
5. Запустить проект с конфигурацией которую настроили выше
