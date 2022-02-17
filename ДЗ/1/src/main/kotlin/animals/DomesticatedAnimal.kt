package animals

import House

abstract class DomesticatedAnimal(
    override val name: String,
    override var weight: Int,
    color: String?,
) : Animal(name, weight, color), CanHaveHome {
    private var home: House? = null

    override fun die() {
        home = null
        super.die()
    }

    override fun setHome(home: House?) {
        this.home = home
    }

    override fun getHome(): House? = home
}