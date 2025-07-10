package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomAccountInfo


internal interface CustomAccountInfoEntityMapper {
    operator fun invoke(customAccountInfo: CustomAccountInfo): CustomAccountInfoEntity
}
