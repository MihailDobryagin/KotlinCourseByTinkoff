import animals.CanHaveHome
import animals.DomesticatedAnimal
import animals.Human

class House(private var owner: Human) {

    private val residents: MutableList<CanHaveHome> = mutableListOf()
    private var pets: MutableList<DomesticatedAnimal> = mutableListOf()

    init {
        addResident(owner)
    }

    fun addResident(resident: CanHaveHome) {
        residents.add(resident)
        resident.setHome(this)
    }

    fun addPet(pet: DomesticatedAnimal) {
        addResident(pet)
        pets.add(pet)
    }

    fun addPets(animals: Iterable<DomesticatedAnimal>) = animals.forEach(this::addPet)

    fun deletePet(animal: DomesticatedAnimal) {
        val predicate = { el: CanHaveHome -> el is DomesticatedAnimal && el.name == animal.name }
        pets.removeIf(predicate)
        residents.removeIf(predicate)
    }

    fun getOwner() = owner

    fun getResidents() = residents
    fun getPets() = pets

    override fun toString(): String {
        return "Residents:\n" +
                residents.toString() + "\n\n" +
                "Pets:\n" +
                pets.toString()
    }
}