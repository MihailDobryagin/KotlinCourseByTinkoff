package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(assignableTypes = [BuildingController::class])
class BuildingControllerHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(BuildingControllerHandler::class.java)
    }

    @ExceptionHandler
    fun exceptionHandler(e: Exception): ResponseEntity<*> {
        logger.error("Неперехваченное исключение", e)
        return ResponseEntity("Произошла ошибка на сервере", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}