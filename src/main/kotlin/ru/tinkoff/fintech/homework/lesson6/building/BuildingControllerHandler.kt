package ru.tinkoff.fintech.homework.lesson6.building

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.tinkoff.fintech.homework.lesson6.building.response.ErrorResponse

@RestControllerAdvice(assignableTypes = [BuildingController::class])
class BuildingControllerHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(BuildingControllerHandler::class.java)
    }

    @ExceptionHandler
    fun exceptionHandler(e: Exception): ErrorResponse {
        logger.error("Неперехваченное исключение", e)
        return ErrorResponse("Произошла ошибка на сервере")
    }
}