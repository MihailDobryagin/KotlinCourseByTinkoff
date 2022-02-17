package animals

class Cat(
    override val name: String,
    color: String?
) : DomesticatedAnimal(name, 5, color) {

    private var countOfEatenMouses: Int = 0

    override fun voice() = println("Meow")

    override fun getEmoji() = "~(=^･･^)"

    fun eatMouse(mouse: Mouse) {
        countOfEatenMouses++
        eat(mouse.weight)
        mouse.die()
    }
}