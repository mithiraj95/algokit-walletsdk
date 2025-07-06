package com.michaeltchuang.wallet.ui

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.ui.AlgoKitEvent
import com.michaeltchuang.walletsdk.runtimeaware.ui.OnBoardingBottomSheet
import com.michaeltchuang.walletsdk.runtimeaware.viewmodel.NameRegistrationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AccountListScreen(
    viewModel: NameRegistrationViewModel = koinViewModel(),
    runtimeAwareSdk: RuntimeAwareSdk
) {
    val accountList by viewModel.uiState.collectAsStateWithLifecycle()
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showConfetti by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            viewModel.getAccount()
        }
    }


    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                Text(text = "Account List", style = typography.title.regular.sansMedium)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Account")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(accountList) { account ->
                AccountItem(account) { address ->
                    scope.launch {
                        viewModel.deleteAccount(address)
                    }
                }
            }
        }
    }

    if (showSheet) {
        OnBoardingBottomSheet(viewModel, runtimeAwareSdk) {
            when (it) {
                AlgoKitEvent.ClOSE_BOTTOMSHEET -> {
                    showSheet = false
                }

                AlgoKitEvent.ALGO25_ACCOUNT_CREATED,
                AlgoKitEvent.HD_ACCOUNT_CREATED -> {
                    // TODO: Show Confetti
                    showConfetti = true
                    showSheet = false
                }
            }
        }
    }

    if (showConfetti) {
        LottieConfetti(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        LaunchedEffect(Unit) {
            delay(5000)
            showConfetti = false
        }
    }
}
