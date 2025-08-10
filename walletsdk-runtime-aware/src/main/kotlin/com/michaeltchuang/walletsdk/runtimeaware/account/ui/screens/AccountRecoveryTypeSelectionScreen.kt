package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingScreens
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.GroupChoiceWidget
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants

@Composable
fun AccountRecoveryTypeSelectionScreen(
    navController: NavController,
    onClick: (message: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = AlgoKitTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        AlgoKitTopBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            onClick = { navController.popBackStack() }
        )
        TitleWidget()
        Spacer(modifier = Modifier.height(30.dp))
        RecoverAnAccountWidget(navController = navController)
        RecoverAnAccountWithQRWidget(navController)
        PairLedgerDeviceWidget(onClick)
        ImportPeraWebWidget(onClick)
        // AlgorandSecureBackupWidget(onClick)
    }
}

@Composable
private fun TitleWidget(isOnHdWallet: Boolean = false) {
    val titleRes = if (isOnHdWallet) {
        R.string.import_a_wallet
    } else {
        R.string.import_an_account
    }

    Text(
        modifier = Modifier.padding(horizontal = 24.dp),
        style = typography.title.regular.sansMedium,
        color = AlgoKitTheme.colors.textMain,
        text = stringResource(titleRes)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecoverAnAccountWidget(
    isOnHdWallet: Boolean = false,
    navController: NavController
) {
    val titleRes: Int
    val descriptionRes: Int
    if (isOnHdWallet) {
        titleRes = R.string.recover_a_wallet
        descriptionRes = R.string.i_want_to_recover_wallet
    } else {
        titleRes = R.string.recover_an_account
        descriptionRes = R.string.i_want_to_recover
    }

    GroupChoiceWidget(
        title = stringResource(id = titleRes),
        description = stringResource(id = descriptionRes),
        icon = ImageVector.vectorResource(R.drawable.ic_key),
        iconContentDescription = stringResource(id = R.string.key),
        onClick = { navController.navigate(OnBoardingScreens.RECOVER_AN_ACCOUNT_SCREEN.name) })
}

@Composable
private fun RecoverAnAccountWithQRWidget(
    navController: NavController
) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.recover_an_account_with_qr),
        description = stringResource(id = R.string.i_want_to_recover_qr),
        icon = ImageVector.vectorResource(R.drawable.ic_qr),
        iconContentDescription = stringResource(id = R.string.qr_code),
        onClick = { navController.navigate(OnBoardingScreens.QR_CODE_SCANNER_SCREEN.name) })
}

@Composable
private fun PairLedgerDeviceWidget(onClick: (message: String) -> Unit) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.pair_ledger_device),
        description = stringResource(id = R.string.i_want_to_recover_an),
        iconContentDescription = stringResource(id = R.string.ledger),
        icon = ImageVector.vectorResource(R.drawable.ic_ledger),
        onClick = { onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET) })
}

@Composable
private fun ImportPeraWebWidget(onClick: (message: String) -> Unit) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.import_from_pera_web),
        description = stringResource(id = R.string.i_want_to_import_algorand),
        iconContentDescription = stringResource(id = R.string.import_from_pera_web),
        icon = ImageVector.vectorResource(R.drawable.ic_global),
        onClick = { onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET) })
}

@Composable
private fun AlgorandSecureBackupWidget(onClick: (message: String) -> Unit) {
    GroupChoiceWidget(
        title = stringResource(id = R.string.algorand_secure_backup),
        description = stringResource(id = R.string.i_want_to_restore_my),
        iconContentDescription = stringResource(id = R.string.i_want_to_restore_my),
        icon = ImageVector.vectorResource(R.drawable.ic_backup),
        onClick = { onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET) })
}

@PreviewLightDark
@Composable
fun AccountRecoveryTypeSelectionScreenPreview() {
    AlgoKitTheme {
        AccountRecoveryTypeSelectionScreen(rememberNavController()) {}
    }

}
