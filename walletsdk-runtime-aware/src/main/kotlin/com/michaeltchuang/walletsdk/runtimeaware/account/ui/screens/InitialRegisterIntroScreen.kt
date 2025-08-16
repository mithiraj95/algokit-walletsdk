package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitScreens
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.OnboardingAccountTypeViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button.AlgoKitTertiaryButton
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitialRegisterIntroScreen(navController: NavController = rememberNavController()) {

    val viewModel: OnboardingAccountTypeViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is OnboardingAccountTypeViewModel.ViewEvent.AccountCreated -> {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("accountCreation", it.accountCreation)
                    navController.navigate(AlgoKitScreens.CREATE_ACCOUNT_NAME.name)
                    Log.d("CreateAccountTypeScreen", it.accountCreation.address)
                }

                is OnboardingAccountTypeViewModel.ViewEvent.Error -> {
                    Log.d("CreateAccountTypeScreen", it.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlgoKitTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                style = typography.title.regular.sansMedium,
                color = AlgoKitTheme.colors.textMain,
                text = stringResource(
                    R.string.welcome_to_pera
                )
            )
            Box(
                modifier = Modifier
                    .weight(1f, fill = true),
                contentAlignment = Alignment.TopEnd
            ) {
                PeraIcon(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.pera_icon_3d),
                    contentDescription = stringResource(id = R.string.welcome_to_pera),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        CreateNewWalletWidget(viewModel)

        Spacer(modifier = Modifier.height(40.dp))

        ImportAccountWidget(navController)

        Spacer(modifier = Modifier.weight(1f))

        TermsAndPrivacy()
    }
}


@Composable
private fun CreateNewWalletWidget(viewModel: OnboardingAccountTypeViewModel) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            style = typography.body.regular.sans,
            text = stringResource(R.string.new_to_algorand),
            color = AlgoKitTheme.colors.textGray,
        )

        Spacer(Modifier.height(12.dp))

        AlgoKitTertiaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                viewModel.createHdKeyAccount()
            },
            text = stringResource(id = R.string.create_a_new_wallet),
            leftIcon = {
                PeraIcon(
                    painter = painterResource(id = R.drawable.ic_hd_wallet),
                    contentDescription = stringResource(id = R.string.hd_wallet),
                )
            },
            rightIcon = {
                PeraIcon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(id = R.string.right_arrow),
                    tintColor = AlgoKitTheme.colors.textGray
                )
            }
        )
    }
}

@Composable
private fun ImportAccountWidget(navController: NavController) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            style = typography.body.regular.sans,
            text = stringResource(R.string.already_have_an_account),
            color = AlgoKitTheme.colors.textGray
        )

        Spacer(Modifier.height(12.dp))

        AlgoKitTertiaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                navController.navigate(AlgoKitScreens.ACCOUNT_RECOVERY_TYPE_SCREEN.name)
            },
            text = stringResource(id = R.string.import_an_account),
            leftIcon = {
                PeraIcon(
                    painter = painterResource(id = R.drawable.ic_key),
                    contentDescription = stringResource(id = R.string.hd_wallet),
                )
            },
            rightIcon = {
                PeraIcon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(id = R.string.right_arrow),
                    tintColor = AlgoKitTheme.colors.textGray
                )
            }
        )
    }
}


@PreviewLightDark
@Composable
private fun InitialRegisterIntroScreenPreview() {
    AlgoKitTheme {
        Column {
            InitialRegisterIntroScreen()
        }
    }
}
