package ru.tinkoff.fintech.homework.lesson7.building

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.tinkoff.fintech.homework.lesson7.utils.ValidationException

@RestControllerAdvice(assignableTypes = [BuildingController::class])
class BuildingControllerHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(BuildingControllerHandler::class.java)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun exceptionHandler(e: ValidationException): String {
        logger.error(e.message, e)
        return "Ошибка валидации"
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exceptionHandler(e: Exception): String {
        logger.error("Необработанное исключение", e)
        return "Внутрення ошибка сервера"
    }
}