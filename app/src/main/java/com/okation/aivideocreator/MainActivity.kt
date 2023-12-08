package com.okation.aivideocreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.okation.aivideocreator.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Purchases.logLevel = LogLevel.DEBUG
        Purchases.configure(PurchasesConfiguration.Builder(this, Constants.GOOGLE_API_KEY).build())
        setContentView(R.layout.activity_main)
    }
}