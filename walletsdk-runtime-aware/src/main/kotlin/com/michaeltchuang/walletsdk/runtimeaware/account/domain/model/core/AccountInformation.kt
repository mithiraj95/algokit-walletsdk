package com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.utils.AssetConstants.ALGO_ID
import java.math.BigInteger

data class AccountInformation(
    val address: String,
    val amount: BigInteger,
    val lastFetchedRound: Long,
    val rekeyAdminAddress: String?,
    val totalAppsOptedIn: Int,
    val totalAssetsOptedIn: Int,
    val totalCreatedApps: Int,
    val totalCreatedAssets: Int,
    val appsTotalExtraPages: Int,
    val appsTotalSchema: AppStateScheme?,
    val assetHoldings: List<AssetHolding>,
    val createdAtRound: Long?
) {

    fun isRekeyed(): Boolean {
        return !rekeyAdminAddress.isNullOrEmpty() && rekeyAdminAddress != address
    }

    fun hasAsset(assetId: Long): Boolean {
        return assetId == ALGO_ID || assetHoldings.any { it.assetId == assetId }
    }

    fun getAssetHoldingIds() = assetHoldings.map { it.assetId }

    fun isCreated() = createdAtRound != null

    fun isThereAnOptedInApp() = totalAppsOptedIn > 0 || totalCreatedApps > 0

    fun isThereAnOptedInAsset() = totalAssetsOptedIn > 0 || totalCreatedAssets > 0
}
