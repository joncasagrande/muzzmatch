package com.jonathan.muzzmatchmessages.viewModel

import androidx.lifecycle.Observer
import com.jonathan.muzzmatchmessages.model.ChatMessage
import com.jonathan.muzzmatchmessages.repo.MessagesRepository
import com.jonathan.muzzmatchmessages.util.PERSONAL_USER_ID
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Test
import java.util.*

class MainActivityViewModelTest : TestCase() {

    private lateinit var viewModel: MainActivityViewModel
    private val repo: MessagesRepository = mockk()
    public override fun setUp() {
        super.setUp()
        viewModel = MainActivityViewModel(repo)
    }

    @Test
    fun `When loadMessage is request, message observer should be triggered`() {
        // arrange
        val chatMessages: Observer<List<ChatMessage>> = mockk(relaxed = true)
        viewModel.messages.observeForever(chatMessages)

        // act
        viewModel.loadMessage()

        // assert
        verify { chatMessages.onChanged(any()) }
    }

    @Test
    fun `When addMessage is triggered, addMessage observer should be triggered`() {
        // arrange
        val calendar = Calendar.getInstance()
        val chatMessages: Observer<ChatMessage> = mockk(relaxed = true)
        viewModel.addMessage.observeForever(chatMessages)
        val contentMessage = "test message"
        val expected = ChatMessage(contentMessage, PERSONAL_USER_ID, calendar.time)

        // act
        viewModel.addMessage(contentMessage)

        // assert
        verify { chatMessages.onChanged(expected) }
    }

    @Test
    fun `When addMessage is triggered, repository is triggered`() {
        // arrange
        val calendar = Calendar.getInstance()
        val contentMessage = "test message"
        val expected = ChatMessage(contentMessage, PERSONAL_USER_ID, calendar.time)

        // act
        viewModel.addMessage(contentMessage,false)

        // assert
        verify { repo.persistMessage(expected) }
    }
}
