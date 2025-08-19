package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountNameViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button.AlgoKitPrimaryButton
import com.michaeltchuang.walletsdk.runtimeaware.utils.toShortenedAddress
import org.koin.androidx.compose.koinViewModel


@Composable
fun CreateAccountNameScreen(
    navController: NavController, onFinish: () -> Unit
) {
    val accountCreation =
        navController.previousBackStackEntry?.savedStateHandle?.get<AccountCreation>("accountCreation")

    accountCreation?.let {
        Log.d("CreateAccountNameScreen", it.address)
    }
    val viewModel: CreateAccountNameViewModel = koinViewModel()
    val viewState = viewModel.state.collectAsStateWithLifecycle().value
    val accountName = remember { mutableStateOf(accountCreation?.address.toShortenedAddress()) }

    LaunchedEffect(Unit) {
        accountCreation?.let { viewModel.fetchAccountDetails(accountCreation) }
    }
    when (viewState) {
        is CreateAccountNameViewModel.ViewState.Content -> {
            ScreenContent(
                navController,
                accountName,
                seedId = viewState.walletId,
                onFinishClick = {
                    accountCreation?.let {
                        viewModel.addNewAccount(
                            it, accountName.value
                        )
                    }
                })
        }

        is CreateAccountNameViewModel.ViewState.Idle -> {}
        is CreateAccountNameViewModel.ViewState.Loading -> {}
    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is CreateAccountNameViewModel.ViewEvent.FinishedAccountCreation -> {
                    onFinish()
                }

                is CreateAccountNameViewModel.ViewEvent.Error -> {
                    Log.d("CreateAccountTypeScreen", it.message)
                }
            }
        }
    }

}

@Composable
fun ScreenContent(
    navController: NavController,
    accountName: MutableState<String>,
    seedId: Int?,
    onFinishClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = AlgoKitTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        AlgoKitTopBar(
            onClick = { navController.popBackStack() })
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
        ) {
            Text(
                style = typography.title.regular.sansBold,
                color = AlgoKitTheme.colors.textMain,
                text = "Name your account",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                style = typography.body.regular.sansMedium,
                color = AlgoKitTheme.colors.textMain,
                text = "Name your account to easily identify it while using the AlgoKit Wallet. These names are stored locally, and can only be seen by you.",
            )

            Spacer(modifier = Modifier.height(24.dp))
            seedId?.let { seedId ->
                WalletItem(seedId)
            }
            Spacer(modifier = Modifier.height(50.dp))

            CustomBasicTextField(accountName.value, {
                accountName.value = it
            }, {
                accountName.value = ""
            })
        }

        AlgoKitPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            {
                onFinishClick()
            },
            text = stringResource(R.string.finish_account_creation),
        )
    }
}

@Composable
fun CustomBasicTextField(
    value: String, onValueChange: (String) -> Unit, onClearClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            style = typography.body.regular.sansMedium,
            color = AlgoKitTheme.colors.textMain,
            text = "Account Name",
        )

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = value,
                cursorBrush = SolidColor(AlgoKitTheme.colors.textMain),
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    color = AlgoKitTheme.colors.textMain, fontSize = 16.sp
                ),
            )
            IconButton(onClick = onClearClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = Color.Gray
                )
            }
        }

        // Bottom Line
        HorizontalDivider(
            thickness = 1.dp, color = Color.Gray
        )
    }
}

@Composable
fun WalletItem(
    seedId: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = AlgoKitTheme.colors.buttonSecondaryBg
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_wallet),
                contentDescription = null,
                tint = AlgoKitTheme.colors.textMain
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Wallet #$seedId",
                style = typography.body.regular.sans,
                color = AlgoKitTheme.colors.textMain,
            )
        }
    }
}


@PreviewLightDark
@Composable
fun CreateAccountNameScreenPreview() {
    AlgoKitTheme {
        ScreenContent(
            navController = rememberNavController(),
            seedId = 0,
            accountName = remember { mutableStateOf("") },
            onFinishClick = {},
        )
    }
}
