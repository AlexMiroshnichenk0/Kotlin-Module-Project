package classes

import java.util.Scanner


class NoteList(val storage: Storage) {
    private val scanner = Scanner(System.`in`).useDelimiter("\\R");

    private var archive: Archive? = null
    private var part: Int = 0
    private var option: Int = 0
    private var loop: Boolean = true

    private fun print(type: Int) {
        val isNote = type == 1

        val obj = if (isNote) archive!! else storage
        val size = obj.list.size

        println("Список ${if (isNote) "заметок" else "архивов"}:")
        println("0. Создать ${if (isNote) "заметку" else "архив"}")

        obj.show()

        println("${size + 1}. Выход")
    }

    private fun inputInt(): Int {
        while (true) {
            println("Введите число")

            val input = scanner.next()

            if (input.all { it in '0'..'9'}) {
                return input.toInt()
            }

            println("Ошибка ввода!")
        }
    }

    private fun create(type: Int) {
        val isNote = type == 1

        println("Введите значение ${if (isNote) "заметки" else "архива"}")

        val name = scanner.next().trim()

        if (name.isEmpty()) {
            println("Название ${if (isNote) "заметки" else "архива"} не может быть пустым!")
        } else {
            if (isNote) {
                println("Введите текст для заметки!")

                val text = scanner.next().trim()

                if (text.isEmpty()) {
                    println("Текст заметки не должен быть пустым!")
                } else {
                    storage.list[option].create(Note(name, text))
                }
            } else {
                storage.create(Archive(name))
            }
        }
    }

    private fun dive(type: Int) {
        val isNote = type == 1
        val obj = if (isNote) archive!! else storage
        val size = obj.list.size

        if (size + 1 == option) {
            println(if (isNote) "Открыть список архивов!" else "Завершить программу!")

            if (isNote) {
                part = 0
            } else {
                loop = false
            }

        } else if (size < option) {
            println("Не корректное число!")
        } else {
            if (isNote) {
                println(archive?.list!![option - 1].text)
            } else {
                archive = storage.list[option - 1]
                part = 1
            }
        }
    }

    fun start() {
        println("Для навигации используйте цифры указанные приложением!")

        while (loop) {

            print(part)

            option = inputInt()

            when (option) {
                0 -> {
                    create(part)
                }

                else -> {
                    if (option >= 0) {
                        dive(part)
                    } else {
                        println("Число не может быть меньше '0'!")
                    }
                }
            }
        }
    }
}