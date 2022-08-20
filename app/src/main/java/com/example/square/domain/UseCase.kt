package com.example.square.domain

abstract class UseCase<in P, R> {

    suspend operator fun invoke(parameters: P): Result<R> {
        return runCatching {
            execute(parameters)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
