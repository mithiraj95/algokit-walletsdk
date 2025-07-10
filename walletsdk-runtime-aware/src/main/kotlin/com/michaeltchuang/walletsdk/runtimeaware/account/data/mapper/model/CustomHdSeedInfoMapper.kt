package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomHdSeedInfo

internal interface CustomHdSeedInfoMapper {
    operator fun invoke(entity: CustomHdSeedInfoEntity): CustomHdSeedInfo
}
