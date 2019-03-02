package art.com.currencyexchanger.models.mappers

import art.com.currencyexchanger.models.domain.CurrencyRate
import art.com.currencyexchanger.models.dto.CurrencyRateResponse

fun CurrencyRateResponse.toCurrencyRate() = CurrencyRate(
    id = this.symbol,
    ask = this.ask,
    bid = this.bid,
    last = this.last,
    open = this.open,
    volume = this.volume,
    timestamp = this.timestamp
)