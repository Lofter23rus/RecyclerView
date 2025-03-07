package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class CustomDecorator : ItemDecoration() {

    private val bounds = Rect()
    private val paint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 1f
    }
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val childCount = parent.childCount
        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)
            parent.getDecoratedBoundsWithMargins(child, bounds)

            val positionCurrent = parent.getChildAdapterPosition(child)
            if (positionCurrent != RecyclerView.NO_POSITION) {
                val lastElementPosition = parent.adapter?.itemCount?.minus(1)
                if (positionCurrent != lastElementPosition) {
                    canvas.drawLine(
                        bounds.left.toFloat() + 150f,
                        bounds.bottom.toFloat(),
                        bounds.right.toFloat(),
                        bounds.bottom.toFloat(),
                        paint
                    )
                }
            }
        }
    }
}