package art.com.currencyexchanger.utilities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Context.inflate(layoutId: Int, parent: ViewGroup? = null): View =
    LayoutInflater.from(this).inflate(layoutId, parent, false)

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) { visibility = if (value) View.VISIBLE else View.GONE }

fun View.onClick(onClick: (view: View) -> Unit) = setOnClickListener { onClick(it) }
