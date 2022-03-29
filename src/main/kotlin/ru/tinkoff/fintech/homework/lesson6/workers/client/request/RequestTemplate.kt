package ru.tinkoff.fintech.homework.lesson6.workers.client.request

import ru.tinkoff.fintech.homework.lesson6.workers.client.SimpleResponse

abstract class RequestTemplate {
    abstract val uri: String
    abstract val responseType: Class<SimpleResponse>
    abstract val dto: Any
}