package art.com.currencyexchanger.data

import art.com.currencyexchanger.interfaces.Repository
import art.com.currencyexchanger.interfaces.RestApi
import art.com.currencyexchanger.interfaces.WebSocketApi
import art.com.currencyexchanger.models.domain.CurrencyPair
import art.com.currencyexchanger.models.domain.CurrencyRate
import art.com.currencyexchanger.models.mappers.toCurrencyPair
import art.com.currencyexchanger.models.mappers.toCurrencyRate
import art.com.currencyexchanger.utilities.WebSocketRequestBuilder
import art.com.currencyexchanger.utilities.baseCall
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryProvider(
    private val restApi: RestApi,
    private val webSocketApi: WebSocketApi,
    private val webSocketRequestBuilder: WebSocketRequestBuilder
): Repository {

    override fun getCurrencyPairs(): Single<List<CurrencyPair>>{
        return restApi.getCurrencyPairs().baseCall().flatMap {response ->
            val list = ArrayList<CurrencyPair>()
            response.forEach { list.add(it.toCurrencyPair()) }
            Single.just(list)
        }
    }

    override fun getCurrencyRate(id: String): Single<CurrencyRate> {
        return restApi.getCurrencyRate(id).baseCall().flatMap { Single.just(it.toCurrencyRate()) }
    }

    override fun subscribeToCurrencyRate(currencyId: String): Single<Boolean> {
        return webSocketApi
            .subscribeTicker(webSocketRequestBuilder.buildTickerSubscribeRequest(currencyId))
            .retry()
            .baseCall()
    }

    override fun unsubscribeToCurrencyRate(currencyId: String): Single<Boolean> {
        return webSocketApi
            .unsubscribeTicker(webSocketRequestBuilder.buildTickerUnsubscribeRequest(currencyId))
            .retry()
            .baseCall()
    }

    override fun getCurrencyRateSubscription(): Observable<CurrencyRate>{
        return webSocketApi.getTickerSubscription()
            .retry()
            .baseCall()
            .flatMap { Observable.just(it.toCurrencyRate())}
    }
}