package ru.tinkoff.fintech.homework.lesson3.animals

class Cat(
    name: String,
    color: String?
) : DomesticatedAnimal(name, 5, color) {

    private var countOfEatenMouses: Int = 0

    fun eatMouse(mouse: Mouse) {
        if (!mouse.alive) {
            println("$this can't eat $mouse, because this mouse is already dead")
            return
        }
        super.eat(mouse.weight)
        countOfEatenMouses++
        mouse.die()
    }

    override fun voice() = println("Meow")

    override fun getEmoji() = "~(=^･･^)"


}