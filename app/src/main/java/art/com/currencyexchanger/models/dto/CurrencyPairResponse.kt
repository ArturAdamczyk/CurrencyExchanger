package art.com.currencyexchanger.models.dto

import com.google.gson.annotations.Expose


data class CurrencyPairResponse(
    @Expose
    val id: String = "",
    @Expose
    val baseCurrency: String = "",
    @Expose
    val quoteCurrency: String = "",
    @Expose
    val quantityIncrement: Float = 0F,
    @Expose
    val tickSize: Float = 0F,
    @Expose
    val takeLiquidityRate: Float = 0F,
    @Expose
    val provideLiquidityRate: Float = 0F,
    @Expose
    val feeCurrency: String = ""
)