package art.com.currencyexchanger.models.dto

import com.google.gson.annotations.Expose

data class CurrencyRateResponse(
    @Expose
    val ask: Float,
    @Expose
    val bid: Float,
    @Expose
    val last: Float,
    @Expose
    val open: Float,
    @Expose
    val low: Float,
    @Expose
    val high: Float,
    @Expose
    val volume: Float,
    @Expose
    val volumeQuote: Float,
    @Expose
    val timestamp: String,
    @Expose
    val symbol: String
)
