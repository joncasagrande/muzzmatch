package com.jonathan.muzzmatchmessages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.muzzmatchmessages.R
import com.jonathan.muzzmatchmessages.adapter.viewHolder.IncomeMessageViewHolder
import com.jonathan.muzzmatchmessages.adapter.viewHolder.SentMessageViewHolder
import com.jonathan.muzzmatchmessages.model.ChatMessage

class ChatMessageAdapter(private val personalId: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<ChatMessage>()

    fun loadChat(messages: List<ChatMessage>){
        items.addAll(messages)
        notifyDataSetChanged()
    }

    fun addMessage(chatMessage: ChatMessage){
        items.add(chatMessage)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHATMESSAGE_SENT -> SentMessageViewHolder(inflate(R.layout.item_sent_message,parent))
            VIEW_TYPE_CHATMESSAGE_INCOME -> IncomeMessageViewHolder(inflate(R.layout.item_income_message,parent))
            else -> throw Exception("Unknown view holder type: $viewType")
        }
    }

    private fun inflate(resId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(resId, parent, false)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SentMessageViewHolder -> holder.bind(items[position])
            is IncomeMessageViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].userId == personalId) {
            true -> VIEW_TYPE_CHATMESSAGE_SENT
            else -> VIEW_TYPE_CHATMESSAGE_INCOME
        }
    }

    companion object {
        const val VIEW_TYPE_CHATMESSAGE_SENT = 0
        const val VIEW_TYPE_CHATMESSAGE_INCOME = 1
    }

}