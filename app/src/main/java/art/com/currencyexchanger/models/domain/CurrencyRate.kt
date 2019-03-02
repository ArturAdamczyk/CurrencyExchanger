package art.com.currencyexchanger.models.domain


data class CurrencyRate(
    val id: String = "",
    val ask: Float = -1F,
    val bid: Float = -1F,
    val last: Float = -1F,
    val open: Float = -1F,
    val volume: Float = -1F,
    val timestamp: String = "",
    var change: Float = -1F
){

    init{
        countChange(this.open, this.last)
    }

    companion object{
        const val EMPTY: Float = -1F
        private const val ZERO: Float = 0F
        private const val PERCENTAGE_MULTIPLIER: Float = 100F
    }

    private fun countChange(open: Float, last: Float){
        val change = open - last
        this.change = if(change != ZERO && open != ZERO) (change / open) * PERCENTAGE_MULTIPLIER else EMPTY
    }

}
