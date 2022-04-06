package ru.tinkoff.fintech.homework.lesson7.workers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.tinkoff.fintech.homework.lesson7.utils.ValidationException

@RestControllerAdvice(assignableTypes = [WorkersController::class])
class WorkersControllerHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersControllerHandler::class.java)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Ошибка валидации")
    fun exceptionHandler(e: ValidationException): String {
        logger.error(e.message, e)
        return "Ошибка валидации"
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exceptionHandler(e: Exception): String {
        logger.error("Неперехваченное исключение", e)
        return "Внутренняя ошибка сервера"
    }
}