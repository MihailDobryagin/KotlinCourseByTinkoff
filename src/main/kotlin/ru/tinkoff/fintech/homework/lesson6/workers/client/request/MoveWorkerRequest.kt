package ru.tinkoff.fintech.homework.lesson6.workers.client.request

import ru.tinkoff.fintech.homework.lesson6.workers.client.SimpleResponse
import ru.tinkoff.fintech.homework.lesson6.workers.client.dto.MoveWorkerDto

data class MoveWorkerRequest(
    override val dto: MoveWorkerDto,
) : RequestTemplate() {
    override val uri = "workers/move"
    override val responseType: Class<SimpleResponse> = SimpleResponse::class.java
}
