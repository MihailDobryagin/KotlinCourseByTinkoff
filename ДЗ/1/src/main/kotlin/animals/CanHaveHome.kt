package animals

import House

interface CanHaveHome {
    fun getHome(): House?
    fun setHome(home: House?)
}