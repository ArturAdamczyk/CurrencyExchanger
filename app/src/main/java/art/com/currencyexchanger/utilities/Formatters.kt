package art.com.currencyexchanger.utilities

import org.joda.time.DateTime

fun float2String(number: Float): String{
    val formatType = if(number > 1) "%.3f" else "%.8f"
    return String.format(formatType, number)
}

fun DateTime.parseServerTimeStamp(): String{
    return "${this.hourOfDay().get()}:${this.minuteOfHour().get()}:${this.secondOfMinute().get()}," +
            " ${this.dayOfMonth().get()}.${this.monthOfYear().get()}.${this.year().get()}"
}