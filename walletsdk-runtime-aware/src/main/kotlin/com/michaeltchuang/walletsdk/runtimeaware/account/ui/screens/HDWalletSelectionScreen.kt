package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountTypeViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button.PeraSecondaryButton
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIcon
import org.koin.androidx.compose.koinViewModel


@Composable
fun HdWalletSelectionScreen(
    navController: NavController
) {
    val viewModel: CreateAccountTypeViewModel = koinViewModel()
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .background(color = AlgoKitTheme.colors.background)
            .fillMaxHeight(.9f)
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Column(
            modifier = Modifier.fillMaxHeight(.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val currentState = viewState) {
                is CreateAccountTypeViewModel.ViewState.Idle -> {}
                is CreateAccountTypeViewModel.ViewState.Loading -> {}
                is CreateAccountTypeViewModel.ViewState.Content -> {
                    ContentState(navController)
                }
            }
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun ContentState(navController: NavController) {
    Box(Modifier.padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
        ) {

            Text(
                style = AlgoKitTheme.typography.title.regular.sansMedium,
                text = stringResource(R.string.select_universal_wallet),
                color = AlgoKitTheme.colors.textMain
            )
            Text(
                style = AlgoKitTheme.typography.body.regular.sans,
                text = stringResource(R.string.create_your_new_account),
                color = AlgoKitTheme.colors.textGray,
                modifier = Modifier.padding(top = 12.dp)
            )
            LazyColumn(modifier = Modifier.padding(top = 24.dp, bottom = 50.dp)) {
                items(1) { walletItemPreview ->
                    with(walletItemPreview) {
                        WalletItem(
                            modifier = Modifier,
                            walletName = "name",
                            numberOfAccounts = "3",
                            primaryValue = "primaryValue",
                            secondaryValue = "secondaryValue",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wallet),
                            iconContentDescription = stringResource(
                                id = R.string.create_a_new_algorand_account_with
                            ),
                            onClick = { })
                    }
                }
            }
        }
        PeraSecondaryButton(
            onClick = {},
            text = stringResource(id = R.string.create_a_new_wallet),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            leftIcon = {
                PeraIcon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = stringResource(id = R.string.plus),
                    modifier = Modifier
                )
            })
    }
}

@Composable
fun WalletItem(
    modifier: Modifier = Modifier,
    walletName: String,
    numberOfAccounts: String,
    primaryValue: String,
    secondaryValue: String,
    icon: ImageVector,
    iconContentDescription: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AlgoKitTheme.colors.layerGrayLighter)
                .padding(8.dp),
            imageVector = icon,
            contentDescription = iconContentDescription,
            tint = AlgoKitTheme.colors.textMain
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                style = AlgoKitTheme.typography.body.regular.sans,
                color = AlgoKitTheme.colors.textMain,
                text = walletName
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                style = AlgoKitTheme.typography.footnote.sans,
                color = AlgoKitTheme.colors.textGrayLighter,
                text = numberOfAccounts
            )
        }
        Column {
            Text(
                style = AlgoKitTheme.typography.body.regular.sansMedium,
                color = AlgoKitTheme.colors.textMain,
                text = primaryValue
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                style = AlgoKitTheme.typography.footnote.sans,
                color = AlgoKitTheme.colors.textGray,
                text = secondaryValue
            )
        }
    }
}