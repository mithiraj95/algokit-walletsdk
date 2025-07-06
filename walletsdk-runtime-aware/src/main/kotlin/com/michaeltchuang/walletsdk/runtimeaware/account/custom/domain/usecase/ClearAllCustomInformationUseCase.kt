package com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.repository.CustomAccountInfoRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.repository.CustomHdSeedInfoRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

internal class ClearAllCustomInformationUseCase(
    private val customAccountInfoRepository: CustomAccountInfoRepository,
    private val customHdSeedInfoRepository: CustomHdSeedInfoRepository
) : ClearAllCustomInformation {

    override suspend fun invoke() {
        withContext(Dispatchers.IO) {
            val clearAccountInfoDeferred =
                async { customAccountInfoRepository.clearAllInformation() }
            val clearHdSeedInfoDeferred = async { customHdSeedInfoRepository.clearAllInformation() }
            awaitAll(clearHdSeedInfoDeferred, clearAccountInfoDeferred)
        }
    }
}
