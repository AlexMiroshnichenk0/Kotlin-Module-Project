package classes

import interfaces.Storage

class Storage() : Storage<Archive> {
    override val list: MutableList<Archive> = mutableListOf()

    override fun show() {
        list.forEachIndexed { i, archive -> println("${i + 1}. ${archive.name}") }
    }

    override fun create(option: Archive) {
        var isContains: Boolean = false

        for (archive in list) {
            if (option.name == archive.name) {
                println("Архив с именем '${option.name}' уже существует")
                isContains = true
                break
            }
        }

        if (!isContains)
            list.add(option)
        println("Архив '${option.name}' создан!")
    }
    }
