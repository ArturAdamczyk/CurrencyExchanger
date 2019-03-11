package art.com.currencyexchanger.framework.presentation.features.currency_rate


import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import art.com.currencyexchanger.R
import art.com.currencyexchanger.framework.presentation.base.BaseFragment
import art.com.currencyexchanger.framework.presentation.features.currency_pairs.CurrencyPairsFragmentArgs
import art.com.currencyexchanger.models.domain.CurrencyRate
import art.com.currencyexchanger.utilities.CurrencyDrawableResolver
import art.com.currencyexchanger.utilities.float2String
import art.com.currencyexchanger.utilities.parseServerTimeStamp
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_currency_rate.*
import org.joda.time.DateTime


class CurrencyRateFragment: BaseFragment<CurrencyRateViewModel>(CurrencyRateViewModel::class) {
    override val layoutId: Int = R.layout.fragment_currency_rate
    private val args: CurrencyPairsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        viewModel.setCurrencyId(args.currencyPairId)
        viewModel.currencyRate.observe(this, Observer {
            refreshUI(it)
        })
        viewModel.getCurrencyRate()
        viewModel.startRefreshService()
        setupUI()
    }

    private fun setupUI(){
        viewModel.currencyRate.value?.let{
            viewModel.lastDrawableId = if (it.change > 0) R.drawable.up else R.drawable.down
            Glide.with(this)
                .load(ContextCompat.getDrawable(this.context!!, viewModel.lastDrawableId))
                .into(currencyRateTrendIcon)
        }
    }

    private fun refreshUI(currencyRate: CurrencyRate){
        if(currencyRate.change != CurrencyRate.EMPTY){
            val trendDrawableId = if (currencyRate.change > 0) R.drawable.up else R.drawable.down
            currencyRateSymbolIcon.visibility = View.VISIBLE
            currencyRateChangeLabel.visibility = View.VISIBLE
            if(trendDrawableId != viewModel.lastDrawableId){
                viewModel.lastDrawableId = trendDrawableId
                Glide.with(this)
                    .load(ContextCompat.getDrawable(this.context!!, trendDrawableId))
                    .into(currencyRateTrendIcon)
            }
            currencyRateChangeLabel.text = resources.getString(R.string.currency_rate_change_value, currencyRate.change)
        }else{
            currencyRateSymbolIcon.visibility = View.INVISIBLE
            currencyRateChangeLabel.visibility = View.INVISIBLE
        }

        currencyRateTitleLabel.text = args.currencyPairId
        currencyRateAskLabelValue.text = float2String(currencyRate.ask)
        currencyRateBidLabelValue.text = float2String(currencyRate.bid)
        currencyRateOpenLabelValue.text = float2String(currencyRate.open)
        currencyRateTimestampLabelValue.text = DateTime(currencyRate.timestamp).parseServerTimeStamp()
        currencyRateVolumeLabelValue.text = float2String(currencyRate.volume)
        currencyRateLastValueLabel.text = float2String(currencyRate.last)
        CurrencyDrawableResolver.resolve(args.baseCurrency).apply {
            Glide.with(context!!)
                .load(if (this != CurrencyDrawableResolver.EMPTY) this else R.drawable.empty)
                .into(currencyRateSymbolIcon)
        }
        if(viewModel.previousLastValue != currencyRate.last){
            viewModel.previousLastValue = currencyRate.last
            currencyRateLastValueLabel.animation = (AnimationUtils.loadAnimation(this.context, R.anim.blink))
        }
    }

    override fun onPause(){
        super.onPause()
        viewModel.unsubscribeRefreshService()
    }
}


