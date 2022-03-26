package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("building")
class BuildingController @Autowired constructor(
    private val buildingService: BuildingService
) {
    @GetMapping("rooms")
    @ResponseBody
    fun getRooms(): Map<Long, Room> {
        return buildingService.getRooms()
    }

    @GetMapping("rooms/add")
    @ResponseBody
    fun addRoom(@RequestParam name: String): Long {
        return buildingService.addRoom(name)
    }
}