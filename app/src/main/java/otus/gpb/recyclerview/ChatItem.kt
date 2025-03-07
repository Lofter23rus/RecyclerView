package otus.gpb.recyclerview

data class ChatItem(
    val id: Int,
    val date: String,
    val name: String,
    val surname: String,
    val message: String,
    val background: Int
)