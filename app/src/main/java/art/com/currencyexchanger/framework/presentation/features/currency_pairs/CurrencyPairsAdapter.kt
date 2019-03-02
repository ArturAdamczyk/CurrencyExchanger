package art.com.currencyexchanger.framework.presentation.features.currency_pairs

import art.com.currencyexchanger.R
import art.com.currencyexchanger.models.domain.CurrencyPair
import art.com.currencyexchanger.utilities.CurrencyDrawableResolver
import art.com.currencyexchanger.utilities.RecyclerAdapter
import art.com.currencyexchanger.utilities.onClick
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_currency_pair.view.*


class CurrencyPairsAdapter: RecyclerAdapter<CurrencyPair>() {
    override val itemLayoutId: Int = R.layout.row_currency_pair

    var onDetailsClick: (subject: CurrencyPair) -> Unit = {}

    override fun ViewHolder.initListeners() {
        this.itemView.onClick {
            onDetailsClick(items[adapterPosition])
        }
    }

    override fun ViewHolder.bind(item: CurrencyPair) {
        itemView.rowCurrencyPairTitleLabel.text = item.id
        CurrencyDrawableResolver.resolve(item.baseCurrency).apply {
            Glide.with(itemView.context)
                .load(if (this != CurrencyDrawableResolver.EMPTY) this else R.drawable.empty)
                .into(itemView.rowCurrencypairSymbolIcon)
        }
    }
}