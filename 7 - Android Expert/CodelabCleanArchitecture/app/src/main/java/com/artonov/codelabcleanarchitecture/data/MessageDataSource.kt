package com.artonov.codelabcleanarchitecture.data

import com.artonov.codelabcleanarchitecture.domain.MessageEntity

class MessageDataSource: IMessageDataSource {
    override fun getMessageFromSource(name: String): MessageEntity {
        return MessageEntity("Hello $name!")
    }
}