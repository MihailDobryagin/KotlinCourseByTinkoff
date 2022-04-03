package utils

class ValidationException(
    override val message: String?
) : IllegalArgumentException()