package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomHdSeedInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdKeyAccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.custom.DeleteHdSeedCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.DeleteHdKeyAccount

class DeleteHdKeyAccountUseCase(
    private val hdKeyAccountRepository: HdKeyAccountRepository,
    private val deleteHdSeedCustomInfo: DeleteHdSeedCustomInfo
) : DeleteHdKeyAccount {

    override suspend fun invoke(address: String) {
        hdKeyAccountRepository.getAccount(address)?.let {
            hdKeyAccountRepository.deleteAccount(it.algoAddress)
            deleteHdSeedCustomInfo.invoke(it.seedId)
        }
    }
}
