package ru.tinkoff.fintech.homework.lesson6.workers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.tinkoff.fintech.homework.lesson6.workers.client.SimpleResponse

@RestControllerAdvice(assignableTypes = [WorkersController::class])
class WorkersControllerHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(WorkersControllerHandler::class.java)
    }

    @ExceptionHandler
    fun exceptionHandler(e: Exception): SimpleResponse {
        logger.error("Неперехваченное исключение", e)
        return SimpleResponse(false, "Произошла ошибка на сервере")
    }
}