package ru.tinkoff.fintech.homework.lesson6.workers.client

import org.springframework.web.client.RestTemplate

class BuildingClient {

    val PATH = "/building"

    private val restTemplate = RestTemplate()

    fun moveWorker(from: Long, to: Long) {
        val reqObj = MoveWorkerObj(from, to)
        doRequest(MoveWorkerRequest)
    }

    private fun doRequest(request: RequestTemplate) {
        restTemplate.postForObject(PATH + request.uri, request.obj, request.responseType)
    }

    private abstract class RequestTemplate {
        abstract val uri: String
        abstract val responseType: Class<*>
        abstract val obj: Any
    }

    private data class MoveWorkerObj(
        val from: Long,
        val to: Long,
    )

    private class MoveWorkerRequest(
        override val obj: MoveWorkerObj,
    ) : RequestTemplate() {
        override val uri = "rooms/add"
        override val responseType = MoveWorkerResponse::class.java
    }

    private data class MoveWorkerResponse(
        val id: Long
    )
}