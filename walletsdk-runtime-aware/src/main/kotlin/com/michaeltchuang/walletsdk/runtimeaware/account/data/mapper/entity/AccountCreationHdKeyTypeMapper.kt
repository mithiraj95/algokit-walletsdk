package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddress

interface AccountCreationHdKeyTypeMapper {
    operator fun invoke(
        entropy: ByteArray,
        hdKeyAddress: HdKeyAddress,
        seedId: Int?
    ): AccountCreation.Type.HdKey
}
