package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountNameViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button.PeraPrimaryButton
import com.michaeltchuang.walletsdk.runtimeaware.utils.toShortenedAddress
import org.koin.androidx.compose.koinViewModel


@Composable
fun CreateAccountNameScreen(
    navController: NavController,
    onFinish: () -> Unit
) {
    val accountCreation = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<AccountCreation>("accountCreation")

    accountCreation?.let {
        Log.d("CreateAccountNameScreen", it.address)
    }
    val viewModel: CreateAccountNameViewModel = koinViewModel()
    var accountName by remember { mutableStateOf(accountCreation?.address.toShortenedAddress()) }


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


    Box(
        modifier = Modifier
            .background(color = AlgoKitTheme.colors.background)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)

    ) {
        AlgoKitTopBar(
            onClick = { navController.popBackStack() }
        )
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

            Spacer(modifier = Modifier.height(50.dp))

            CustomBasicTextField(accountName, {
                accountName = it
            }, {
                accountName = ""
            })
        }

        PeraPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            {
                accountCreation?.let {
                    viewModel.addNewAccount(
                        it,
                        accountName
                    )
                }
            },
            text = "Finish Account Creation",
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

@PreviewLightDark
@Composable
fun CreateAccountNameScreenPreview() {
    AlgoKitTheme {
        CreateAccountNameScreen(
            navController = rememberNavController()
        ) {

        }
    }
}
