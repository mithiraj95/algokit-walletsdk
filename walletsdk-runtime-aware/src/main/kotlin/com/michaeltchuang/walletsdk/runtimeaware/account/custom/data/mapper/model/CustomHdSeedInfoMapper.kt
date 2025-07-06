package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomHdSeedInfo


internal interface CustomHdSeedInfoMapper {
    operator fun invoke(entity: CustomHdSeedInfoEntity): CustomHdSeedInfo
}