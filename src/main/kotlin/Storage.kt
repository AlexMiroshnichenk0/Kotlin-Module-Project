package interfaces

interface  Storage<T> {
    val list: MutableList<T>

    fun show()
    fun create(option: T)
}