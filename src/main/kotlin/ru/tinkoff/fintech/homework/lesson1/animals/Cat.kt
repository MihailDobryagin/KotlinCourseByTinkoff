package ru.tinkoff.fintech.homework.lesson1.animals

class Cat(
    name: String,
    color: String?
) : DomesticatedAnimal(name, 5, color) {

    private var countOfEatenMouses: Int = 0

    fun eatMouse(mouse: Mouse) {
        countOfEatenMouses++
        eat(mouse.weight)
        mouse.die()
    }

    override fun voice() = println("Meow")

    override fun getEmoji() = "~(=^･･^)"


}