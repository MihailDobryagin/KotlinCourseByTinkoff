package ru.tinkoff.fintech.homework.lesson6.workers.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.tinkoff.fintech.homework.lesson6.workers.client.dto.MoveWorkerDto
import ru.tinkoff.fintech.homework.lesson6.workers.client.request.MoveWorkerRequest

@Component
class BuildingClient(
    private val restTemplate: RestTemplate,
) {
    private val PATH = "building/"

    fun moveWorker(from: Long?, to: Long?): Boolean {
        val requestDto = MoveWorkerDto(from, to)
        val request = MoveWorkerRequest(requestDto)
        val result = restTemplate.postForEntity(PATH + request.uri, request.dto, SimpleResponse::class.java).body
        return result?.success ?: false
    }
}