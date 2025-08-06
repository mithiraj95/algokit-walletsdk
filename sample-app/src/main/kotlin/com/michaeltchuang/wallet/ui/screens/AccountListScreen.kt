package com.michaeltchuang.wallet.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.michaeltchuang.wallet.ui.components.AccountItem
import com.michaeltchuang.wallet.ui.components.LottieConfetti
import com.michaeltchuang.wallet.ui.navigation.QREvent
import com.michaeltchuang.wallet.ui.viewmodel.AccountListViewModel
import com.michaeltchuang.wallet.ui.viewmodel.AccountListViewModel.AccountsEvent
import com.michaeltchuang.wallet.ui.viewmodel.AccountListViewModel.AccountsState
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackbarViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitEvent
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingBottomSheet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

private const val CONFETTI_DURATION = 5000L
private val FAB_PADDING = 32.dp

@Composable
fun AccountListScreen(
    navController: NavController,
    snackbarViewModel: SnackbarViewModel,
    tag: String,
) {
    val viewModel: AccountListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val events = viewModel.viewEvent.collectAsStateWithLifecycle(initialValue = null)
    var showSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var showConfetti by remember { mutableStateOf(false) }
    var qrScanFlow by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        QREvent.qrClickEvent.collect {
            qrScanFlow = it
            showSheet = it
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchAccounts()
    }

    LaunchedEffect(events.value) {
        events.value?.let { event ->
            handleEvent(event, context)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    qrScanFlow = false
                    showSheet = true
                },
                modifier = Modifier.padding(end = FAB_PADDING, bottom = FAB_PADDING),
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Account")
            }
        },
    ) { padding ->
        AccountListContent(
            state = state,
            padding = padding,
            onDeleteAccount = { address ->
                scope.launch {
                    viewModel.deleteAccount(address)
                }
            },
        )
    }

    OnBoardingBottomSheet(showSheet = showSheet, qrScanFlow = qrScanFlow) { event ->
        handleBottomSheetEvent(
            event = event,
            onCloseSheet = { showSheet = false },
            onAccountCreated = {
                showConfetti = true
                showSheet = false
                scope.launch {
                    viewModel.fetchAccounts()
                }
            },
        )
    }

    if (showConfetti) {
        ConfettiEffect(
            onComplete = { showConfetti = false },
        )
    }
}

@Composable
private fun AccountListContent(
    state: AccountsState,
    padding: PaddingValues,
    onDeleteAccount: (String) -> Unit,
) {
    when (state) {
        AccountsState.Idle -> {
            CenteredMessage("Ready to load accounts...")
        }

        AccountsState.Loading -> {
            CenteredLoader()
        }

        is AccountsState.Content -> {
            AccountsList(
                accounts = state.accounts,
                padding = padding,
                onDeleteAccount = onDeleteAccount,
            )
        }

        is AccountsState.Error -> {
            CenteredMessage(
                text = "Error: ${state.message}",
                color = Color.Red,
            )
        }
    }
}

@Composable
private fun CenteredMessage(
    text: String,
    color: Color = Color.Unspecified,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, color = color)
    }
}

@Composable
private fun CenteredLoader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun AccountsList(
    accounts: List<LocalAccount>,
    padding: PaddingValues,
    onDeleteAccount: (String) -> Unit,
) {
    if (accounts.isEmpty()) {
        CenteredMessage("No accounts found. Tap '+' to add one!")
    } else {
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                accounts,
                key = { it.algoAddress },
            ) { account ->
                AccountItem(account) { address ->
                    onDeleteAccount(address)
                }
                Log.d("AccountItem", "Total accounts: ${accounts.size}")
            }
        }
    }
}

@Composable
private fun ConfettiEffect(onComplete: () -> Unit) {
    LottieConfetti(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
    )

    LaunchedEffect(Unit) {
        delay(CONFETTI_DURATION)
        onComplete()
    }
}

private fun handleEvent(
    event: AccountsEvent,
    context: android.content.Context,
) {
    when (event) {
        is AccountsEvent.ShowError -> {
            Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
        }

        is AccountsEvent.ShowMessage -> {
            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
        }
    }
}

private fun handleBottomSheetEvent(
    event: AlgoKitEvent,
    onCloseSheet: () -> Unit,
    onAccountCreated: () -> Unit,
) {
    when (event) {
        AlgoKitEvent.ClOSE_BOTTOMSHEET -> {
            onCloseSheet()
        }

        AlgoKitEvent.ALGO25_ACCOUNT_CREATED,
        AlgoKitEvent.HD_ACCOUNT_CREATED,
        -> {
            onAccountCreated()
        }
    }
}
