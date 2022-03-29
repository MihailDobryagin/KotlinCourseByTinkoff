package ru.tinkoff.fintech.homework.lesson6.workers.client.requests

import ru.tinkoff.fintech.homework.lesson6.workers.client.requests.dto.MoveWorkerDto
import ru.tinkoff.fintech.homework.lesson6.workers.client.responses.SimpleResponse

data class MoveWorkerRequest(
    override val dto: MoveWorkerDto,
) : RequestTemplate() {
    override val uri = "workers/move"
    override val responseType = SimpleResponse::class.java
}
