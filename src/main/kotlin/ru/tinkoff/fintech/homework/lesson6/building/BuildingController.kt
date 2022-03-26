package ru.tinkoff.fintech.homework.lesson6.building

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("building")
class BuildingController @Autowired constructor(
    private val buildingService: BuildingService
) {
    @GetMapping("rooms")
    @ResponseBody
    fun getRooms(): Map<String, Int> {
        return buildingService.getRooms()
    }
}