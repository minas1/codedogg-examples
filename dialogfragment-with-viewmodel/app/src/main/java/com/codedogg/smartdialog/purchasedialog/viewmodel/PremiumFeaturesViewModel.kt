package com.codedogg.smartdialog.purchasedialog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedogg.smartdialog.purchasedialog.model.PurchaseState

class PremiumFeaturesViewModel : ViewModel() {

    private val _price = MutableLiveData<String?>()
    private val _purchaseState = MutableLiveData<PurchaseState?>()

    val price: LiveData<String?> get() = _price
    val purchaseState: LiveData<PurchaseState?> get() = _purchaseState

    init {
        loadPrice()
    }

    private fun loadPrice() {

        // Simulate calling a remote API by adding some delay.
        Thread {
            Thread.sleep(1500)
            _price.postValue("€2.99") // We are not on the main thread, so use `postValue()`.
        }.start()
    }

    fun doPurchase() {

        _purchaseState.value = PurchaseState.PURCHASE_IN_PROGRESS

        // Simulate calling a remote API by adding some delay.
        Thread {
            Thread.sleep(1500)
            _purchaseState.postValue(PurchaseState.PURCHASE_SUCCESSFUL)
        }.start()
    }

    class Factory : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PremiumFeaturesViewModel() as T
    }
}