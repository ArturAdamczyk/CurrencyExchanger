package art.com.currencyexchanger.utilities

import art.com.currencyexchanger.interfaces.AppMessage
import art.com.currencyexchanger.interfaces.WebSocketApi
import art.com.currencyexchanger.models.dto.CurrencyRateResponse
import art.com.currencyexchanger.models.dto.CurrencyRateWebSocketResponse
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observables.ConnectableObservable
import okhttp3.*
import okio.ByteString

class WebSocketClient(
    private val okHttpClient: OkHttpClient,
    private val messenger: AppMessage
): WebSocketApi {
    private lateinit var INSTANCE: WebSocket
    private lateinit var subscription: ConnectableObservable<CurrencyRateResponse>

    override fun subscribeTicker(request: String): Single<Boolean> = Single.create{ INSTANCE?.send(request) }

    override fun getTickerSubscription(): Observable<CurrencyRateResponse> = subscription

    override fun unsubscribeTicker(request: String): Single<Boolean> =  Single.create{ INSTANCE?.send(request) }

    override fun start(request: Request) {
        subscription = ConnectableObservable.create<CurrencyRateResponse>{ emitter ->
            INSTANCE = WebSocketFactory.connect(object : WebSocketListener() {
                private val NORMAL_CLOSURE_STATUS = 1000
                override fun onOpen(webSocket: WebSocket?, response: Response?) {
                    messenger.logMessage(CLASS_TAG, response!!.message())
                    messenger.logMessage(CLASS_TAG, response.message())
                }

                override fun onMessage(webSocket: WebSocket?, text: String?) {
                    messenger.logMessage(CLASS_TAG, text!!)
                    try{
                        val webSocketData = Gson().fromJson<CurrencyRateWebSocketResponse>(text, CurrencyRateWebSocketResponse::class.java)
                        webSocketData.params?.let {
                            emitter.onNext(webSocketData.params)
                        }
                    }catch(e: JsonParseException){
                        messenger.logMessage(CLASS_TAG, e.toString())
                    }
                }

                override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
                    messenger.logMessage(CLASS_TAG, bytes!!.hex())
                }

                override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                    webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
                    messenger.logMessage(CLASS_TAG, reason!!)
                }

                override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
                    messenger.logMessage(CLASS_TAG, t!!.message!!)
                }
            }, okHttpClient, request)
        }.publish()
        subscription.connect()
    }

    companion object {
        private val CLASS_TAG = WebSocketClient::class.java.simpleName
    }
}