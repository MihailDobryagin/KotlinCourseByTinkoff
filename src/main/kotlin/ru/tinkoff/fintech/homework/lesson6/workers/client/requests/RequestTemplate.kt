package ru.tinkoff.fintech.homework.lesson6.workers.client.requests

abstract class RequestTemplate {
    abstract val uri: String
    abstract val responseType: Class<*>
    abstract val dto: Any
}