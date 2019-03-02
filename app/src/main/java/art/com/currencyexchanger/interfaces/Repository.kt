package art.com.currencyexchanger.interfaces

import art.com.currencyexchanger.models.domain.CurrencyPair
import art.com.currencyexchanger.models.domain.CurrencyRate
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {

    fun getCurrencyPairs(): Single<List<CurrencyPair>>

    fun getCurrencyRate(id: String): Single<CurrencyRate>

    fun subscribeToCurrencyRate(currencyId: String): Single<Boolean>

    fun unsubscribeToCurrencyRate(currencyId: String): Single<Boolean>

    fun getCurrencyRateSubscription(): Observable<CurrencyRate>
}