import animals.Cat
import animals.Human
import animals.Mouse

fun main(args: Array<String>) {
    val cat = Cat("Boris", "brown with white stripes")
    val human = Human("Anatoliy", 75)
    val mouse = Mouse()

    listOf(human, cat, mouse).forEach {
        println(it.getEmoji())
        it.voice()
        println("color : ${it.getColor()}")
        println()
    }

    printLine()

    for (i in 1..5)
        mouse.eat(i)
    cat.eatMouse(mouse)


    println("${cat.name}'s ${cat.getEmoji()} weight after morning breakfast is ${cat.weight}")

    human.work(24)
    human.eat(4)

    println("${human.name}'s mood is ${human.getMood()}" + if (human.getMood() < 5) "(" else ")")

    printLine()

    val house = House(human)
    house.addPet(cat)

    println(house)
    printLine()
    house.deletePet(cat)
    println(house)

}

private fun printLine() = println("\n--------------------------------------------------------------------------\n")