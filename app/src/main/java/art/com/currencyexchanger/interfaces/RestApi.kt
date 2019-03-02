package art.com.currencyexchanger.interfaces

import art.com.currencyexchanger.models.dto.CurrencyPairResponse
import art.com.currencyexchanger.models.dto.CurrencyRateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("symbol")
    fun getCurrencyPairs(): Single<List<CurrencyPairResponse>>

    @GET("ticker/{symbol}")
    fun getCurrencyRate(@Path("symbol") symbol: String): Single<CurrencyRateResponse>
}