package art.com.currencyexchanger.utilities

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<T> : RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>() {
    abstract val itemLayoutId: Int
    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: (item: T) -> Unit = {}

    open fun ViewHolder.initListeners() = Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(itemLayoutId))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    abstract fun ViewHolder.bind(item: T)

    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.onClick { onClick(items[adapterPosition]) }
            this.initListeners()
        }
    }

    private fun ViewGroup.inflate(layoutId: Int): View = this.context.inflate(layoutId, this)

}
