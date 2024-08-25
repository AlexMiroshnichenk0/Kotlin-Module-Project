import classes.NoteList
import classes.Storage

fun main(args: Array<String>) {
val storage = Storage()
    val hey = NoteList(storage)
    hey.start()
}