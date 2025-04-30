package com.artonov.codelabcleanarchitecture.data

import com.artonov.codelabcleanarchitecture.domain.IMessageRepository
import com.artonov.codelabcleanarchitecture.domain.MessageEntity

class MessageRepository(private val messageDataSource: IMessageDataSource) : IMessageRepository {
    override fun getWelcomeMessage(name: String): MessageEntity =
        messageDataSource.getMessageFromSource(name)
}