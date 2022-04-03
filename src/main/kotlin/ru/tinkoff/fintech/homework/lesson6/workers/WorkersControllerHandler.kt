package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import utils.ValidationException

@RestControllerAdvice(assignableTypes = [WorkersController::class])
class WorkersControllerHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersControllerHandler::class.java)
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun exceptionHandler(e: ValidationException) {
        logger.error(e.message, e)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exceptionHandler(e: Exception) {
        logger.error("Неперехваченное исключение", e)
    }
}