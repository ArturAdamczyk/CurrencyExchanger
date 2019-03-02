package art.com.currencyexchanger.framework.presentation.features.currency_pairs


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.*

import art.com.currencyexchanger.framework.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_currency.*
import art.com.currencyexchanger.R
import art.com.currencyexchanger.framework.AppConfig


class CurrencyPairsFragment: BaseFragment<CurrencyPairsViewModel>(CurrencyPairsViewModel::class) {
    override val layoutId: Int = R.layout.fragment_currency
    private val currencyPairsAdapter = CurrencyPairsAdapter()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        viewModel.currencyPairs.observe(this, Observer {
                currencyPairsAdapter.items = it
                refreshUI()
        })
        viewModel.getCurrencyPairs()
        viewModel.fetchError.observe(this, Observer {
                refreshUI(true)
        })
    }

    private fun setupUI(view: View){
        rootView = view
        navController = view.findNavController()
        initRecyclerView()
        currencyPairsAdapter.onDetailsClick = {item ->
            if (navController.currentDestination?.id == R.id.currenciesFragment) {
                navController.navigate(
                    R.id.action_currenciesFragment_to_currencyRateFragment,
                    Bundle().also {
                        it.putString(AppConfig.INTENT_CURRENCY_PAIR_ID, item.id)
                        it.putString(AppConfig.INTENT_BASE_CURRENCY_ID, item.baseCurrency)
                    }
                )
            }
        }
        currencyPairsRefreshIcon.setOnClickListener {
            viewModel.getCurrencyPairs()
        }
    }

    private fun initRecyclerView() {
        with(currencyPairsRecyclerView) {
            adapter = currencyPairsAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun refreshUI(fetchError: Boolean = false){
        val condition = fetchError && currencyPairsAdapter.items.isEmpty()
        val visibility = if(condition) View.VISIBLE else View.GONE
        currencyPairsErrorLabel.visibility = visibility
        currencyPairsRefreshIcon.visibility = visibility
        if (condition) passMessage(resources.getString(R.string.currency_pairs_api_error))
    }
}