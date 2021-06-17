package com.jonathan.muzzmatchmessages

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.muzzmatchmessages.adapter.ChatMessageAdapter
import com.jonathan.muzzmatchmessages.databinding.ActivityMainBinding
import com.jonathan.muzzmatchmessages.persistence.dao.ChatMessageDao
import com.jonathan.muzzmatchmessages.repo.MessagesRepository
import com.jonathan.muzzmatchmessages.util.FRIEND_NAME
import com.jonathan.muzzmatchmessages.util.PERSONAL_USER_ID
import com.jonathan.muzzmatchmessages.viewModel.MainActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

        viewModel = MainActivityViewModel(MessagesRepository(createDAO()))
        GlobalScope.launch {
            viewModel.initMessage()
        }
        viewModel.messages.observe(this, {
            adapter.loadChat(it)
        })
        viewModel.addMessage.observe(this, {
            adapter.addMessage(it)
            binding.messageEditText.setText("")
        })

        GlobalScope.launch {
            viewModel.loadMessage()
        }
    }

    private fun setupUI() {
        setupToolbar()
        binding.button.setOnClickListener {
            when (binding.messageEditText.text.toString().isNotEmpty()) {
                true -> {
                    GlobalScope.launch {
                        viewModel.addMessage(
                            binding.messageEditText.text.toString(),
                            binding.checkBox.isChecked
                        )
                    }
                }

                else -> Toast.makeText(
                    this@MainActivity,
                    getString(R.string.please_type_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.chatRV.layoutManager = LinearLayoutManager(this)
        adapter = ChatMessageAdapter(PERSONAL_USER_ID)
        binding.chatRV.adapter = adapter
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbar)
        binding.toolbarTitle.text = FRIEND_NAME
    }


    //Migrate to DI
    private fun createDAO(): ChatMessageDao {
        return (application as MuzzmatchApplication).getDatabase().chatMessageDao()
    }
}