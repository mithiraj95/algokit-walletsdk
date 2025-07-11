package com.michaeltchuang.wallet.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.michaeltchuang.wallet.ui.components.AccountItem
import com.michaeltchuang.wallet.ui.components.LottieConfetti
import com.michaeltchuang.wallet.ui.viewmodel.AccountListViewModel
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackbarViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitEvent
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingBottomSheet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountListScreen(
    navController: NavController,
    snackbarViewModel: SnackbarViewModel,
    tag: String,
) {
    val viewModel: AccountListViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showConfetti by remember { mutableStateOf(false) }
    var fetchAccount by remember { mutableStateOf(false) }

    LaunchedEffect(fetchAccount) {
        scope.launch {
            viewModel.processIntent(AccountListViewModel.AccountsIntent.FetchAccount)
            fetchAccount = false
        }
    }



    Scaffold(
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
            contentPadding = padding,
            modifier =
                Modifier
                    .fillMaxSize()
        ) {
            Log.d("AccountListScreen01", state.accounts.size.toString())
            items(state.accounts) { account ->
                AccountItem(account) { address ->
                    scope.launch {
                        viewModel.processIntent(
                            AccountListViewModel.AccountsIntent.DeleteAccount(
                                address
                            )
                        )
                    }
                }
                Log.d("AccountItem", state.accounts.size.toString())
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
                scope.launch {
                    fetchAccount = true

                }
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
