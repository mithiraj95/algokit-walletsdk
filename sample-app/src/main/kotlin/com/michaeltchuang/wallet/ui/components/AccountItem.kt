package com.michaeltchuang.wallet.ui.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme.typography

@Composable
fun AccountItem(
    account: LocalAccount.Algo25,
    onDelete: (address: String) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(Modifier.fillMaxWidth(.9f)) {
                Text(
                    text = account.algoAddress,
                    style = typography.body.regular.monoMedium,
                    modifier = Modifier.padding(end = 8.dp),
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

@Preview(showBackground = true)
@Composable
fun AccountItemPreview() {
    // Create a sample account for preview
    val sampleAccount =
        LocalAccount.Algo25(
            algoAddress = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        )

    AlgoKitTheme {
        AccountItem(
            account = sampleAccount,
            onDelete = { /* Preview - no action */ },
        )
    }
}
