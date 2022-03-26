package ru.tinkoff.fintech.homework.lesson6.workers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("workers")
class WorkersController @Autowired constructor(
    private val workersService: WorkersService,
) {
    @GetMapping
    @ResponseBody
    fun get() {

    }
}