package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomAccountInfo


internal class CustomAccountInfoMapperImpl : CustomAccountInfoMapper {

    override fun invoke(
        address: String,
        customAccountInfoEntity: CustomAccountInfoEntity?
    ): CustomAccountInfo {
        return CustomAccountInfo(
            address = address,
            customName = customAccountInfoEntity?.customName,
            orderIndex = customAccountInfoEntity?.orderIndex ?: 0,
            isBackedUp = customAccountInfoEntity?.isBackedUp ?: false
        )
    }
}
