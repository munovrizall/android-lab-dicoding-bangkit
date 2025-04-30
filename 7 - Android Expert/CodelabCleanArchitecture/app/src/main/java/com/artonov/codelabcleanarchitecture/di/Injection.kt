package com.artonov.codelabcleanarchitecture.di

import com.artonov.codelabcleanarchitecture.data.IMessageDataSource
import com.artonov.codelabcleanarchitecture.data.MessageDataSource
import com.artonov.codelabcleanarchitecture.data.MessageRepository
import com.artonov.codelabcleanarchitecture.domain.IMessageRepository
import com.artonov.codelabcleanarchitecture.domain.MessageInteractor
import com.artonov.codelabcleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}