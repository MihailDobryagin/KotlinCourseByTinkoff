package animals

import House
import kotlin.math.max
import kotlin.math.min

class Human(
    override val name: String,
    override var weight: Int,
) : Animal(name, weight), CanHaveHome {

    private var mood = 10
    private var home: House? = null

    override fun voice() = println("Hello!")

    override fun getEmoji() = "('_')"

    fun work(hours: Int) {
        weight = max(1, weight - hours)
        mood = max(0, mood - hours / 2)
    }

    override fun eat(foodWeight: Int) {
        super.eat(foodWeight)
        mood = min(10, mood + foodWeight)
    }

    fun getMood() = mood

    override fun setHome(home: House?) {
        this.home = home
    }

    override fun getHome(): House? = home
}