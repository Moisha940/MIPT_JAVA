javac -d bin src/*.java
( echo "1"; # Создадим студента
  echo "Миша";

  echo "2"; # Добавить оценку
  echo "9";

  echo "2"; # Добавить оценку
  echo "8";

  echo "2"; # Добавить оценку
  echo "7";

  echo "4"; # Вывести студента

  echo "3"; # удалить оценку
  echo "7";

  echo "4"; # Вывести студента

  echo "3"; # удалить оценку
  echo "8"

  echo "5"; # Выход
) | java -classpath ./bin Main