package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomAccountInfo


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
