package ru.tinkoff.fintech.homework.lesson7.workers.client

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import ru.tinkoff.fintech.homework.lesson7.workers.client.dto.MoveWorkerDto

@Component
class BuildingClient(
    private val restTemplate: RestTemplate,
) {
    fun moveWorker(from: Long?, to: Long?): Boolean {
        val requestDto = MoveWorkerDto(from, to)
        val result = restTemplate.postForEntity<Void>("/building/workers/move", requestDto)
        return result.statusCode == HttpStatus.OK
    }
}