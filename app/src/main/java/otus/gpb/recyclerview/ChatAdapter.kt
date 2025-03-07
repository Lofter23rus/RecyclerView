package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class ChatAdapter() : ListAdapter<ChatItem, RecyclerView.ViewHolder>(DiffUtilItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList.getOrNull(position)
        item?.let {
            (holder as ChatViewHolder).bind(item)
        }
    }

    fun exchange(fromPosition: Int, toPosition: Int) {
        val list = currentList.toMutableList()
        if (fromPosition < toPosition) {
            for (index in fromPosition until toPosition) {
                Collections.swap(list, index, index + 1)
            }
        } else {
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(list, index, index - 1)
            }
        }
        submitList(list)
    }

    fun removeItem(fromPosition: Int) {
        val list = currentList.toMutableList()
        list.removeAt(fromPosition)
        submitList(list)
    }
}

class DiffUtilItem : DiffUtil.ItemCallback<ChatItem>() {

    override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return oldItem == newItem
    }
}