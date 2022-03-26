package ru.tinkoff.fintech.homework.lesson6.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.tinkoff.fintech.homework.lesson6.services.Service

@RestController("/")
class Controller @Autowired constructor(
    private val service: Service
) {
    @GetMapping("/get")
    @ResponseBody
    fun get(@RequestParam id: Long) : Long {
        return TODO()
    }
}