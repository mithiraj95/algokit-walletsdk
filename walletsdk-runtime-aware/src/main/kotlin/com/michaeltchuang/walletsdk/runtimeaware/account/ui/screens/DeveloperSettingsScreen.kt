package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitScreens
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.SettingsItem
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.OnboardingAccountTypeViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants
import org.koin.androidx.compose.koinViewModel

private const val TAG = "CreateAccountTypeScreen"

@Composable
fun DeveloperSettingsScreen(navController: NavController, onClick: (message: String) -> Unit) {
    val viewModel: OnboardingAccountTypeViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is OnboardingAccountTypeViewModel.ViewEvent.AccountCreated -> {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("accountCreation", it.accountCreation)
                    navController.navigate(AlgoKitScreens.CREATE_ACCOUNT_NAME.name)
                    Log.d(TAG, it.accountCreation.address)
                }

                is OnboardingAccountTypeViewModel.ViewEvent.Error -> {
                    Log.d(TAG, it.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlgoKitTheme.colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        AlgoKitTopBar(
            title = stringResource(R.string.developer_settings),
            onClick = { navController.popBackStack() })

        SettingsItem(
            R.drawable.ic_node,
            stringResource(R.string.node_settings)
        ) {
            onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET)
        }

        SettingsItem(
            R.drawable.ic_wallet,
            stringResource(R.string.create_legacy_algo25_account)
        ) {
            viewModel.createAlgo25Account()
        }
    }

}

@PreviewLightDark
@Composable
fun DeveloperSettingsScreenPreview() {
    AlgoKitTheme {
        DeveloperSettingsScreen(navController = rememberNavController()) {

        }
    }
}
