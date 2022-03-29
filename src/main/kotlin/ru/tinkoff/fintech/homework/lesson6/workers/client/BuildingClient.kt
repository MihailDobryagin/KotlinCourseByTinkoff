package ru.tinkoff.fintech.homework.lesson6.workers.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.tinkoff.fintech.homework.lesson6.workers.client.requests.MoveWorkerRequest
import ru.tinkoff.fintech.homework.lesson6.workers.client.requests.RequestTemplate
import ru.tinkoff.fintech.homework.lesson6.workers.client.requests.dto.MoveWorkerDto

@Component
class BuildingClient @Autowired constructor(
    globalUri: String,
) {
    private val PATH = globalUri + "building/"

    private val restTemplate = RestTemplate()

    fun moveWorker(from: Long, to: Long) {
        val requestDto = MoveWorkerDto(from, to)
        doRequest(MoveWorkerRequest(requestDto))
    }

    private fun doRequest(request: RequestTemplate) {
        restTemplate.postForObject(PATH + request.uri, request.dto, request.responseType)
    }
}