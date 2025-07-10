package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.DeleteAlgo25Account

class DeleteAlgo25AccountUseCase(
    private val algo25AccountRepository: Algo25AccountRepository,
) : DeleteAlgo25Account {

    override suspend fun invoke(address: String) {
        algo25AccountRepository.deleteAccount(address)
    }
}
