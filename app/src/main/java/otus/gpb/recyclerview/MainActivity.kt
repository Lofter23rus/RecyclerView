package otus.gpb.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val adapter: ChatAdapter by lazy { ChatAdapter() }
    private var list: List<ChatItem> = emptyList()
    private var chartIdx: Int = 0
    private val maxChartIdx: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.addItemDecoration(
            CustomDecorator()
        )

        ItemTouchHelper(ItemTouchCallback()).attachToRecyclerView(recyclerView)

        recyclerView.adapter = adapter
        list = loadList(10)
        adapter.submitList(list)

        findViewById<FloatingActionButton>(R.id.loadMore).setOnClickListener { addChats() }
    }

    private fun addChats() {
        //val currentList = list.toMutableList()
        val currentList = adapter.currentList.toMutableList()
        currentList.addAll(loadList(10))
        list = currentList
        adapter.submitList(list)
    }

    private fun loadList(count: Int) = run {
        val list = mutableListOf<ChatItem>()
        if (chartIdx > maxChartIdx) {
            val text = "Все данные подгружены"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            list.toList()
        } else {
            for(n in chartIdx + 1..chartIdx + count){
                val personItem = ChatItem(
                    id = n,
                    name = "Name_$n",
                    surname = "Surname_$n",
                    message = "Messages from Name_$n",
                    date = "00:00",
                    background = ColorGenerator.generateColor()
                )
                list.add(personItem)
            }
            chartIdx += count
            list.toList()
        }
    }
}