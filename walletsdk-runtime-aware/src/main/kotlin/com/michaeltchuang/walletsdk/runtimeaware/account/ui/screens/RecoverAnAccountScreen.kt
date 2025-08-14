package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitScreens
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.PeraCard
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.text.AlgoKitHighlightedGrayText
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.text.AlgoKitHighlightedGreenText
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.text.AlgoKitTitleText
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants


@Composable
fun RecoverAnAccountScreen(
    navController: NavController,
    onClick: (message: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(AlgoKitTheme.colors.background)
    ) {
        AlgoKitTopBar(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .wrapContentSize(),
            onClick = { navController.popBackStack() }
        )

        AlgoKitTitleText(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = R.string.bottom_sheet_mnemonic_type_title)
        )
        Spacer(Modifier.height(24.dp))
        PeraCard(
            title = stringResource(R.string.mnemonic_type_universal_title),
            description = stringResource(R.string.mnemonic_type_universal_description),
            footer = stringResource(R.string.mnemonic_type_universal_footer),
            highlightContent = {
                AlgoKitHighlightedGreenText(
                    text = stringResource(R.string.new_text)
                )
            },
            onClick = {
                onClick(WalletSdkConstants.FEATURE_NOT_SUPPORTED_YET)
            }
        )

        PeraCard(
            title = stringResource(R.string.mnemonic_type_algo25_title),
            description = stringResource(R.string.mnemonic_type_algo25_description),
            footer = stringResource(R.string.mnemonic_type_algo25_footer),
            highlightContent = {
                AlgoKitHighlightedGrayText(
                    text = stringResource(R.string.legacy_text)
                )
            },
            onClick = {
                navController.navigate(AlgoKitScreens.RECOVER_PHRASE_SCREEN.name + "/")
            }
        )
    }
}

@PreviewLightDark
@Composable
fun RecoverAnAccountScreenPreview() {
    AlgoKitTheme {
        RecoverAnAccountScreen(navController = rememberNavController()) {

        }
    }
}
