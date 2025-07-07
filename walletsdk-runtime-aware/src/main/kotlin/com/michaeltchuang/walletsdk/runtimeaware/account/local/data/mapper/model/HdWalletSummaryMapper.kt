package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.HdWalletSummary

internal interface HdWalletSummaryMapper {
    operator fun invoke(entity: HdKeyEntity, accountCount: Int): HdWalletSummary
}