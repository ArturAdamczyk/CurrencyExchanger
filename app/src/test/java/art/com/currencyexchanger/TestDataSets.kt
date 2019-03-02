package art.com.currencyexchanger

import art.com.currencyexchanger.models.domain.CurrencyPair
import art.com.currencyexchanger.models.domain.CurrencyRate
import art.com.currencyexchanger.models.dto.CurrencyPairResponse
import art.com.currencyexchanger.models.dto.CurrencyRateResponse

class TestDataSets {

    private object CurrencyPairSet{
        val set_1 = arrayListOf(
            CurrencyPair("BCNBTC", "BCN", "BTC"),
            CurrencyPair("XRPBTC", "XRP", "BTC"),
            CurrencyPair("ELECUSD", "ELEC", "USD"))
    }

    private object CurrencyPairResponseSet{
        val set_1 = arrayListOf(
            CurrencyPairResponse("BCNBTC", "BCN", "BTC",100F, 0.00000000001F, 0.001F, -0.0001F, "BTC"),
            CurrencyPairResponse("XRPBTC", "XRP", "BTC", 0.1F, 0.000000001F, 0.002F, -0.0003F, "BTC"),
            CurrencyPairResponse("ELECUSD", "ELEC", "USD", 10F, 0.00000000001F, 0.001F, -0.0001F, "USD"))
    }

    private object CurrencyRateSet{
        val set_1 = CurrencyRate("BTCUSD",3827.98F,3827.97F,3827.98F,3828F,40485.58109F, "2019-03-01T12:45:00.370Z", 5.229763E-4F)
        val set_2 = CurrencyRate("BTCUSD",3827.98F,3827.97F,3827.98F,3828F,40485.58109F, "2019-03-01T12:45:00.370Z", 2F)
    }

    private object CurrencyRateResponseSet{
        val set_1 = CurrencyRateResponse(3827.98F,3827.97F,3827.98F,3828F,3777.00F,3895.84F,40485.58109F,155208035.3880319F,"2019-03-01T12:45:00.370Z","BTCUSD")
    }

    fun getCurrencyPairSet(): List<CurrencyPair>{
        return CurrencyPairSet.set_1
    }

    fun getCurrencyPairsResponseSet(): List<CurrencyPairResponse>{
        return CurrencyPairResponseSet.set_1
    }

    fun getCurrencyRateSet(setNumber: String): CurrencyRate{
        return when(setNumber){
            "1"-> CurrencyRateSet.set_1
            "2" -> CurrencyRateSet.set_2
            else -> CurrencyRateSet.set_1
        }
    }

    fun getCurrencyRateResponseSet(): CurrencyRateResponse{
        return CurrencyRateResponseSet.set_1
    }

}