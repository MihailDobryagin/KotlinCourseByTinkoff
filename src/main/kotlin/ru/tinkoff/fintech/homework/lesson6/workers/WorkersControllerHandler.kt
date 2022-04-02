package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(assignableTypes = [WorkersController::class])
class WorkersControllerHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersControllerHandler::class.java)
    }

    @ExceptionHandler
    fun exceptionHandler(e: Exception): ResponseEntity<*> {
        logger.error("Неперехваченное исключение", e)
        return ResponseEntity("Произошла ошибка на сервере", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}