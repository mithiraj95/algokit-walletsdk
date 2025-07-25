package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.OnboardingAccountType
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.OnBoardingScreens
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.RecoverPassphraseViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button.PeraPrimaryButton
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants.SAMPLE_ALGO25_MNEMONIC
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryPhraseScreen(
    navController: NavController,
    mnemonic: String
) {
    val viewModel: RecoverPassphraseViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is RecoverPassphraseViewModel.ViewEvent.NavigateToAccountNameScreen -> {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("accountCreation", it.accountCreation)
                    navController.navigate(OnBoardingScreens.CREATE_ACCOUNT_NAME.name)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .background(color = AlgoKitTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp)

    ) {
        Column {
            AlgoKitTopBar(
                onClick = { navController.popBackStack() }
            )
            RecoveryPhraseContent(
                modifier = Modifier.fillMaxHeight(0.8f),
                predefinedWords = mnemonic.toList()
            )
            Spacer(modifier = Modifier.height(32.dp))
            PeraPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                {
                    when (mnemonic.toList().size) {
                        OnboardingAccountType.Algo25.wordCount -> {
                            viewModel.onRecoverButtonClick(mnemonic, OnboardingAccountType.Algo25)
                        }

                        OnboardingAccountType.HdKey.wordCount -> {}
                    }
                },
                text = stringResource(R.string.finish_account_creation),
            )
        }
    }
}

@Composable
fun RecoveryPhraseContent(
    modifier: Modifier, predefinedWords: List<String>
) {
    val recoveryWords = remember { mutableStateListOf(*predefinedWords.toTypedArray()) }
    Column(modifier = modifier) {
        Text(
            style = AlgoKitTheme.typography.title.regular.sansMedium,
            text = stringResource(R.string.enter_your_recovery_passphrase),
            color = AlgoKitTheme.colors.textMain
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()
        ) {
            val wordCount = recoveryWords.size
            items((wordCount + 1) / 2) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val leftIndex = rowIndex
                    val rightIndex = rowIndex + (wordCount + 1) / 2

                    if (leftIndex < wordCount) {
                        RecoveryWordField(
                            index = leftIndex,
                            value = recoveryWords[leftIndex],
                            onValueChange = { recoveryWords[leftIndex] = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (rightIndex < wordCount) {
                        RecoveryWordField(
                            index = rightIndex,
                            value = recoveryWords[rightIndex],
                            onValueChange = { recoveryWords[rightIndex] = it },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun RecoveryWordField(
    index: Int, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = "${index + 1}",
            style = AlgoKitTheme.typography.title.regular.sans,
            fontSize = 14.sp,
            color = AlgoKitTheme.colors.textMain
        )

        Column {
            Text(
                text = value,
                fontSize = 16.sp,
                color = AlgoKitTheme.colors.textMain,
                style = AlgoKitTheme.typography.title.regular.sans
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                thickness = 1.dp, color = Color.Gray
            )
        }

    }
}

fun String.toList(): List<String> {
    return this.trim().split("\\s+".toRegex())
        .filter { it.isNotBlank() }
}

@PreviewLightDark
@Composable
fun RecoveryPhraseScreenPreview() {
    val words = SAMPLE_ALGO25_MNEMONIC
    RecoveryPhraseScreen(
        rememberNavController(), words
    )
}
