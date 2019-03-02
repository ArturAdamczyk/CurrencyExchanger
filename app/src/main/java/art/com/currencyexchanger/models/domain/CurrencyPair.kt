package art.com.currencyexchanger.models.domain

data class CurrencyPair(
    val id: String = "",
    val baseCurrency: String = "",
    val quoteCurrency: String = ""
)