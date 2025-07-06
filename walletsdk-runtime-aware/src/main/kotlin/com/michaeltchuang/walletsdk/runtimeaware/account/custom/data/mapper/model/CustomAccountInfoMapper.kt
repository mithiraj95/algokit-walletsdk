package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomAccountInfo


internal interface CustomAccountInfoMapper {
    operator fun invoke(
        address: String,
        customAccountInfoEntity: CustomAccountInfoEntity?
    ): CustomAccountInfo
}
