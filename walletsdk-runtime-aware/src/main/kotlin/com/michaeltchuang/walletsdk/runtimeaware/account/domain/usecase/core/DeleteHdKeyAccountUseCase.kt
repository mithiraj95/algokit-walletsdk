package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdKeyAccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.DeleteHdKeyAccount

class DeleteHdKeyAccountUseCase(
    private val hdKeyAccountRepository: HdKeyAccountRepository,
) : DeleteHdKeyAccount {

    override suspend fun invoke(address: String) {
        hdKeyAccountRepository.deleteAccount(address)
    }
}
