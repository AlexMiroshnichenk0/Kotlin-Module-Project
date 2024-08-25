package classes

import interfaces.Storage
import javax.swing.text.html.Option

class Archive(val name: String) : Storage<Note> {
    override val list: MutableList<Note> = mutableListOf()

    override fun show() {
        list.forEachIndexed { i, note -> println("${i + 1}. ${note.name}") }
    }

    override fun create(option: Note) {
        var isContains: Boolean = false

        for (note in list) {
            if (option.name == note.name) {
                println("Заметка с именем '${option.name}' уже существует!")
                isContains = true
                break
            }
        }

        if (!isContains) {
            list.add(option)
            println("Заметка '${option.name}' создана!")
        }
    }
}