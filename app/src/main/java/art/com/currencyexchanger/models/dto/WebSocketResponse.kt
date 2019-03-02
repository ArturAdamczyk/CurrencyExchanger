package art.com.currencyexchanger.models.dto

import com.google.gson.annotations.Expose

abstract class WebSocketResponse<T>(
    @Expose
    val jsonrpc: String,
    @Expose
    val method: String,
    @Expose
    val params: T)
