#!/bin/bash

# старт приложения

# Запрос логина
read -p "Введите логин: " username
# Запрос пароля (скрытый ввод)
read -p "Введите пароль: " password

export USERNAME="$username"
export PASSWORD="$password"

while true
do
    echo
    # Запрос адреса в файловой системе
    read -p "Введите адрес в файловой системе (или введите 'stop' для завершения): " file_address

    if [ "$file_address" = "stop" ]; then
        echo "Остановка скрипта."
      # shellcheck disable=SC2046
        kill $(pgrep -f 'java -jar ./target/SpringHw.jar')
        break
    fi

    # Использование curl для передачи данных
    # /Users/moisha/cmpt130/Java/IdeaProjects/SpringHw/students/test1
    # /Users/moisha/cmpt130/Java/IdeaProjects/SpringHw/students/test2

    curl -X GET "http://localhost:8080/save/user?file_address=$file_address"
done | java -jar ./target/SpringHw.jar > foreach.log 2>&1
