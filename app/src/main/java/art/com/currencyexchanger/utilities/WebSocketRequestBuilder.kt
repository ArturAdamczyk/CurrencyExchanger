package art.com.currencyexchanger.utilities

import okhttp3.Request

class WebSocketRequestBuilder {
    private val subscribeMethod = "subscribeTicker"
    private val unsubscribeMethod = "unsubscribeTicker"

    fun buildTickerSubscribeRequest(currencyId: String): String{
        return buildTickerRequest(currencyId, subscribeMethod)
    }

    fun buildTickerUnsubscribeRequest(currencyId: String): String{
        return buildTickerRequest(currencyId, unsubscribeMethod)
    }

    fun buildWebSocketStartRequest(address: String): Request{
        return Request.Builder()
            .get()
            .url(address)
            .build()
    }

    private fun buildTickerRequest(currencyId: String, method: String): String{
        return "{\n" +
                "  \"method\": \"$method\",\n" +
                "  \"params\": {\n" +
                "    \"symbol\": \"$currencyId\"\n" +
                "  }\n" +
                "}"
    }
}