package art.com.currencyexchanger.models.mappers

import art.com.currencyexchanger.models.domain.CurrencyPair
import art.com.currencyexchanger.models.dto.CurrencyPairResponse

fun CurrencyPairResponse.toCurrencyPair() = CurrencyPair(
    id = this.id,
    baseCurrency = this.baseCurrency,
    quoteCurrency = this.quoteCurrency
)