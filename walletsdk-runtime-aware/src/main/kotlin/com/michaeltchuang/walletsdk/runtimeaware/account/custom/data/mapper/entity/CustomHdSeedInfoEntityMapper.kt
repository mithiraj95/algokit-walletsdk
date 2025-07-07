package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomHdSeedInfo


internal interface CustomHdSeedInfoEntityMapper {
    operator fun invoke(entropyInformation: CustomHdSeedInfo): CustomHdSeedInfoEntity
}
