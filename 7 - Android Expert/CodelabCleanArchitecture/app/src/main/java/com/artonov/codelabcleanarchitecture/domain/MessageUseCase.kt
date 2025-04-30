package com.artonov.codelabcleanarchitecture.domain

interface MessageUseCase {
    fun getMessage(name: String): MessageEntity
}