package ru.tinkoff.fintech.homework.lesson6.building.request.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MoveWorkerDto(
    @JsonProperty("from")
    val from: Long?,
    @JsonProperty("to")
    val to: Long?,
)
