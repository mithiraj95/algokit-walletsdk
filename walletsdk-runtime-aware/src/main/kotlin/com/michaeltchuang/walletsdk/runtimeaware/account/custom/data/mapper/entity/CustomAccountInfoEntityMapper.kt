package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomAccountInfo


internal interface CustomAccountInfoEntityMapper {
    operator fun invoke(customAccountInfo: CustomAccountInfo): CustomAccountInfoEntity
}
