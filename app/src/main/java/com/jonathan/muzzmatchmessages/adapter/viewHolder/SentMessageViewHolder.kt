package com.jonathan.muzzmatchmessages.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.muzzmatchmessages.databinding.ItemSentMessageBinding
import com.jonathan.muzzmatchmessages.model.ChatMessage

class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemSentMessageBinding.bind(itemView)

    fun bind(item: ChatMessage){
        binding.messageText.text = item.content
    }
}