package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomHdSeedInfo


internal interface CustomHdSeedInfoEntityMapper {
    operator fun invoke(entropyInformation: CustomHdSeedInfo): CustomHdSeedInfoEntity
}
