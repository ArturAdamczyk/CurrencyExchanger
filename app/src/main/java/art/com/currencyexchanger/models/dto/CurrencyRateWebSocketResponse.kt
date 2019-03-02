package art.com.currencyexchanger.models.dto


class CurrencyRateWebSocketResponse(
    jsonrcp: String,
    method: String,
    params: CurrencyRateResponse
): WebSocketResponse<CurrencyRateResponse>(jsonrcp, method, params)