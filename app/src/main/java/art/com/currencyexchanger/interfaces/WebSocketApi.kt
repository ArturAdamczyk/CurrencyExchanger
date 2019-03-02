package art.com.currencyexchanger.interfaces

import art.com.currencyexchanger.models.dto.CurrencyRateResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Request


interface WebSocketApi {
    fun subscribeTicker(request: String): Single<Boolean>
    fun unsubscribeTicker(request: String): Single<Boolean>
    fun getTickerSubscription(): Observable<CurrencyRateResponse>
    fun start(request: Request)
}