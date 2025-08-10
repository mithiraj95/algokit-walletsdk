package com.michaeltchuang.wallet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michaeltchuang.wallet.R
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountRegistrationType
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.AccountLite
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIconRoundShape
import com.michaeltchuang.walletsdk.runtimeaware.utils.toShortenedAddress

@Composable
fun AccountItem(
    account: AccountLite,
    onDelete: (address: String) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeraIconRoundShape(
                modifier = Modifier,
                imageVector = ImageVector.vectorResource(getWalletIcon(account.registrationType)),
                contentDescription = "Wallet Icon",
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth(.9f)
                        .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = account.customName.ifEmpty { account.address.toShortenedAddress() },
                    style = typography.body.large.sansMedium,
                )
                Text(
                    text = getAccountType(account.registrationType),
                    style = typography.footnote.mono,
                )
            }

            IconButton(onClick = {
                onDelete(account.address)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Account")
            }
        }
    }
}

fun getWalletIcon(localAccount: AccountRegistrationType): Int =
    when (localAccount) {
        is AccountRegistrationType.HdKey -> {
            R.drawable.ic_hd_wallet
        }

        is AccountRegistrationType.Algo25 -> {
            R.drawable.ic_wallet
        }

        else -> {
            R.drawable.ic_wallet
        }
    }

fun getAccountType(localAccount: AccountRegistrationType): String =
    when (localAccount) {
        is AccountRegistrationType.HdKey -> {
            "HD"
        }

        is AccountRegistrationType.Algo25 -> {
            "Algo25"
        }

        is AccountRegistrationType.NoAuth -> {
            "Watch"
        }

        is AccountRegistrationType.LedgerBle -> {
            "Ledger"
        }
    } + " Account"

@Preview(showBackground = true)
@Composable
fun AccountItemPreview() {
    // Create a sample account for preview
    val sampleAccount =
        AccountLite(
            address = "ASDFGHJKLASDFGHJKL",
            customName = "Sample Account",
            registrationType = AccountRegistrationType.Algo25,
        )

    AlgoKitTheme {
        AccountItem(
            account = sampleAccount,
            onDelete = { /* Preview - no action */ },
        )
    }
}
