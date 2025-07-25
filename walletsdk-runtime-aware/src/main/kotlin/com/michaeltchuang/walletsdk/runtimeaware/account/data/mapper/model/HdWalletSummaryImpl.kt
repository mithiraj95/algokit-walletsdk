package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.HdWalletSummary

internal class HdWalletSummaryMapperImpl : HdWalletSummaryMapper {
    // TODO: set primary and secondary values
    override fun invoke(entity: HdKeyEntity, accountCount: Int): HdWalletSummary {
        return HdWalletSummary(
            seedId = entity.seedId,
            accountCount = accountCount,
            maxAccountIndex = entity.account,
            primaryValue = "",
            secondaryValue = ""
        )
    }
}
