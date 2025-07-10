package com.michaeltchuang.wallet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.michaeltchuang.wallet.ui.screens.AccountListScreen
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoKitTheme {
                AccountListScreen()
            }
        }
    }
}
