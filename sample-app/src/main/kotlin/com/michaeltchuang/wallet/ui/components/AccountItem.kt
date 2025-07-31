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
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.icon.PeraIconRoundShape
import com.michaeltchuang.walletsdk.runtimeaware.utils.toShortenedAddress

@Composable
fun AccountItem(
    account: LocalAccount,
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeraIconRoundShape(
                modifier = Modifier,
                imageVector = ImageVector.vectorResource(getWalletIcon(account)),
                contentDescription = "Wallet Icon"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = account.algoAddress.toShortenedAddress(),
                    style = typography.body.large.sansMedium
                )
                Text(
                    text = getAccountType(account),
                    style = typography.body.regular.mono
                )
            }

            IconButton(onClick = {
                onDelete(account.algoAddress)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Account")
            }
        }
    }
}

fun getWalletIcon(localAccount: LocalAccount): Int {
    return when (localAccount) {
        is LocalAccount.HdKey -> {
            R.drawable.ic_hd_wallet
        }

        is LocalAccount.Algo25 -> {
            R.drawable.ic_wallet
        }

        else -> {
            R.drawable.ic_wallet
        }
    }
}

fun getAccountType(localAccount: LocalAccount): String {
    return when (localAccount) {
        is LocalAccount.HdKey -> {
            "HD Wallet"
        }

        is LocalAccount.Algo25 -> {
            "Algo25"
        }

        is LocalAccount.NoAuth -> {
            "Watch"
        }

        is LocalAccount.LedgerBle -> {
            "Ledger"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountItemPreview() {
    // Create a sample account for preview
    val sampleAccount =
        LocalAccount.HdKey(
            algoAddress = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            publicKey = ByteArray(0),
            seedId = 0,
            account = 0,
            change = 0,
            keyIndex = 0,
            derivationType = 0,
        )

    AlgoKitTheme {
        AccountItem(
            account = sampleAccount,
            onDelete = { /* Preview - no action */ },
        )
    }
}
