package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomHdSeedInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.custom.CustomHdSeedInfoRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdSeedRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetSeedIdIfExistingEntropy
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.utils.clearFromMemory
import com.michaeltchuang.walletsdk.runtimeaware.foundation.AlgoKitResult
import com.michaeltchuang.walletsdk.runtimeaware.utils.base64DecodeToByteArray
import com.michaeltchuang.walletsdk.runtimeaware.utils.base64EncodeToString


internal class AddHdSeedUseCase (
    private val hdSeedRepository: HdSeedRepository,
    private val customHdSeedInfoRepository: CustomHdSeedInfoRepository,
   // private val peraBip39Sdk: AlgoKitBip39Sdk,
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val getSeedIdIfExistingEntropy: GetSeedIdIfExistingEntropy
) : AddHdSeed {

    override suspend fun invoke(entropy: ByteArray): AlgoKitResult<Int> {
        val existingSeedId = getSeedIdIfExistingEntropy.invoke(entropy)
        return if (existingSeedId != null) {
            AlgoKitResult.Success(existingSeedId)
        } else {
            createNewSeed(entropy)
        }
    }

    private suspend fun createNewSeed(entropy: ByteArray): AlgoKitResult<Int> {
        val seed = runtimeAwareSdk.algoKitBit39Sdk()?.getSeedFromEntropy(entropy.base64EncodeToString())?.base64DecodeToByteArray()
            ?: return AlgoKitResult.Error(Exception("Failed to generate seed from entropy"))
        val newSeedId = addHdSeed(seed.copyOf(), entropy)
        setCustomInfo(newSeedId)
        seed.clearFromMemory()
        return AlgoKitResult.Success(newSeedId)
    }

    private suspend fun addHdSeed(seed: ByteArray, entropy: ByteArray): Int {
        return hdSeedRepository.addHdSeed(
            seedId = 0, // ID will be auto-generated
            seed = seed,
            entropy = entropy
        ).toInt()
    }

    private suspend fun setCustomInfo(seedId: Int) {
        customHdSeedInfoRepository.setCustomInfo(
            CustomHdSeedInfo(
                seedId = seedId,
                entropyCustomName = "Wallet #$seedId",
                orderIndex = seedId,
                isBackedUp = false
            )
        )
    }
}
