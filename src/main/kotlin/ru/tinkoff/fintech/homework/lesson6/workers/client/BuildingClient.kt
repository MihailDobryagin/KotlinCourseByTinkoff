package ru.tinkoff.fintech.homework.lesson6.workers.client

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import ru.tinkoff.fintech.homework.lesson6.workers.client.dto.MoveWorkerDto
import ru.tinkoff.fintech.homework.lesson6.workers.client.request.MoveWorkerRequest
import ru.tinkoff.fintech.homework.lesson6.workers.client.request.RequestTemplate

@Component
class BuildingClient(
    private val restTemplate: RestTemplate,
) {
    private val PATH = "building/"

    fun moveWorker(from: Long?, to: Long?): Boolean {
        val requestDto = MoveWorkerDto(from, to)
        val movingResult = doRequest(MoveWorkerRequest(requestDto)).body as Map<*, *>
        return movingResult["success"] as Boolean
    }

    private fun doRequest(request: RequestTemplate): ResponseEntity<Any> {
        return restTemplate.postForEntity(PATH + request.uri, request.dto)
    }
}