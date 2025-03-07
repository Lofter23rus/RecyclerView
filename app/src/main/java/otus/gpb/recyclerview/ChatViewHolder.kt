package otus.gpb.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(
    private val view: View,
) : RecyclerView.ViewHolder(view) {

    init {
        println(this)
    }

    private val name: TextView by lazy { view.findViewById(R.id.name) }
    private val avatar: TextView by lazy { view.findViewById(R.id.avatar) }
    private val message: TextView by lazy { view.findViewById(R.id.message) }
    private val date: TextView by lazy { view.findViewById(R.id.date) }

    fun bind(item: ChatItem) {
        println("bind item ${item.id}")
        name.text = "${item.name} ${item.surname}"
        message.text = item.message
        date.text = item.date
        avatar.text = item.name.substring(0, 1)
        avatar.setBackgroundColor(item.background)
    }
}