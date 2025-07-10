package com.michaeltchuang.wallet.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.michaeltchuang.wallet.ui.components.AccountItem
import com.michaeltchuang.wallet.ui.components.LottieConfetti
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitEvent
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingBottomSheet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AccountListScreen() {
    val accountList = remember { mutableStateOf(emptyList<LocalAccount.Algo25>()) }
    val context = LocalContext.current
    val runtimeAwareSdk = remember { RuntimeAwareSdk(context) }
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showConfetti by remember { mutableStateOf(false) }
    var fetchAccount by remember { mutableStateOf(true) }

    LaunchedEffect(fetchAccount) {
        scope.launch {
            accountList.value = runtimeAwareSdk.fetchAccounts()
            fetchAccount = false
            Log.d("AccountListScreen", accountList.value.size.toString())
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Account List", style = typography.title.regular.sansMedium)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSheet = true },
                modifier =
                    Modifier.padding(
                        end = 32.dp,
                        bottom = 32.dp,
                    ),
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Account")
            }
        },
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
        ) {
            Log.d("AccountListScreen01", accountList.value.size.toString())
            items(accountList.value, key = { it.algoAddress }) { account ->
                AccountItem(account) { address ->
                    scope.launch {
                        runtimeAwareSdk.deleteAccount(address)
                        fetchAccount = true
                    }
                }
                Log.d("AccountItem", accountList.value.size.toString())
            }
        }
    }

    OnBoardingBottomSheet(showSheet) {
        when (it) {
            AlgoKitEvent.ClOSE_BOTTOMSHEET -> {
                showSheet = false
            }

            AlgoKitEvent.ALGO25_ACCOUNT_CREATED,
            AlgoKitEvent.HD_ACCOUNT_CREATED,
                -> {
                showConfetti = true
                showSheet = false
                fetchAccount = true
            }
        }
    }

    if (showConfetti) {
        LottieConfetti(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
        )
        LaunchedEffect(Unit) {
            delay(5000)
            showConfetti = false
        }
    }
}
