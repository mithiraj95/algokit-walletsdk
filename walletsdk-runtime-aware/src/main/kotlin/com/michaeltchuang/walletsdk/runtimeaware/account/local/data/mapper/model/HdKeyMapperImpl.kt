package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdKeyEntity


internal class HdKeyMapperImpl : HdKeyMapper {
    override fun invoke(entity: HdKeyEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey {
        return com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey(
            algoAddress = entity.algoAddress,
            publicKey = entity.publicKey,
            seedId = entity.seedId,
            account = entity.account,
            change = entity.change,
            keyIndex = entity.keyIndex,
            derivationType = entity.derivationType
        )
    }
}
