package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import utils.ValidationException

@RestControllerAdvice(assignableTypes = [BuildingController::class])
class BuildingControllerHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(BuildingControllerHandler::class.java)
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun exceptionHandler(e: ValidationException) {
        logger.error(e.message, e)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exceptionHandler(e: Exception) {
        logger.error("Необработанное исключение", e)
    }
}