package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomAccountInfo


internal interface CustomAccountInfoMapper {
    operator fun invoke(
        address: String,
        customAccountInfoEntity: CustomAccountInfoEntity?
    ): CustomAccountInfo
}
