package com.codedogg.smartdialog

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.codedogg.smartdialog.purchasedialog.view.PremiumFeaturesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val purchaseButton = findViewById<Button>(R.id.purchase)
        purchaseButton.setOnClickListener {
            PremiumFeaturesFragment().show(supportFragmentManager, null)
        }
    }
}