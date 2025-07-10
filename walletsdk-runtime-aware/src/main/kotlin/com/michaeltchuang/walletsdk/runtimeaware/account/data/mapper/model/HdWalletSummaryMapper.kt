package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.HdWalletSummary

internal interface HdWalletSummaryMapper {
    operator fun invoke(entity: HdKeyEntity, accountCount: Int): HdWalletSummary
}