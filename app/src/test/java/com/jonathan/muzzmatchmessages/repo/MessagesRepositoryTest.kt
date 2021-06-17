package com.jonathan.muzzmatchmessages.repo

import androidx.lifecycle.Observer
import com.jonathan.muzzmatchmessages.model.ChatMessage
import com.jonathan.muzzmatchmessages.persistence.dao.ChatMessageDao
import com.jonathan.muzzmatchmessages.util.PERSONAL_USER_ID
import com.jonathan.muzzmatchmessages.viewModel.MainActivityViewModel
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Test
import java.util.*

class MessagesRepositoryTest : TestCase() {

    private lateinit var repo: MessagesRepository
    private val dao: ChatMessageDao = mockk()
    public override fun setUp() {
        super.setUp()
        repo = MessagesRepository(dao)
    }

    @Test
    fun `When loadMessage is request, message observer should be triggered`() {
        // arrange
        val chatMessages: Observer<List<ChatMessage>> = mockk(relaxed = true)

        // act
        repo.getMessages().observeForever(chatMessages)

        // assert
        verify { chatMessages.onChanged(any()) }
    }

    @Test
    fun `When addMessage is triggered, addMessage observer should be triggered`() {
        // arrange
        val calendar = Calendar.getInstance()
        val contentMessage = "test message"
        val expected = ChatMessage(contentMessage, PERSONAL_USER_ID, calendar.time)
        val chatMessages: Observer<ChatMessage> = mockk(relaxed = true)

        // act
        repo.persistMessage(expected).observeForever(chatMessages)

        // assert
        verify { chatMessages.onChanged(expected) }
    }
}