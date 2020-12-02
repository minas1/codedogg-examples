package com.codedogg.smartdialog.purchasedialog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.codedogg.smartdialog.R
import com.codedogg.smartdialog.purchasedialog.model.PurchaseState
import com.codedogg.smartdialog.purchasedialog.viewmodel.PremiumFeaturesViewModel

class PremiumFeaturesFragment : DialogFragment() {

    private lateinit var viewModel: PremiumFeaturesViewModel

    private lateinit var priceView: TextView
    private lateinit var purchaseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, PremiumFeaturesViewModel.Factory())
            .get(PremiumFeaturesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.premium_features, container, false)

        priceView = v.findViewById(R.id.price)
        purchaseButton = v.findViewById(R.id.purchase)

        purchaseButton.setOnClickListener { viewModel.doPurchase() }

        observePrice()
        observePurchaseState()

        return v
    }

    private fun observePrice() = viewModel.price.observe(this) { price ->
        purchaseButton.isEnabled = price != null
        if (price != null) {
            priceView.text = price
        }
    }

    private fun observePurchaseState() = viewModel.purchaseState.observe(this) { purchaseState ->
        when (purchaseState) {
            PurchaseState.PURCHASE_IN_PROGRESS -> purchaseButton.isEnabled = false
            PurchaseState.PURCHASE_SUCCESSFUL -> {
                // The purchase was successful! Show a message and dismiss the dialog.
                Toast.makeText(requireContext(), R.string.purchase_successful, Toast.LENGTH_SHORT).show()
                dismissAllowingStateLoss()
            }
            PurchaseState.PURCHASE_ERROR -> {
                purchaseButton.isEnabled = true // enable so that the user can try again
                Toast.makeText(requireContext(), R.string.purchase_error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}