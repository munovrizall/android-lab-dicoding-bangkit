package com.artonov.codelabcleanarchitecture.data

import com.artonov.codelabcleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}