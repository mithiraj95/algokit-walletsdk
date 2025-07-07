package com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.usecase


import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.usecase.DeleteAlgo25Account


class DeleteAlgo25AccountUseCase(
    private val algo25AccountRepository: Algo25AccountRepository,
) : DeleteAlgo25Account {

    override suspend fun invoke(address: String) {
        algo25AccountRepository.deleteAccount(address)
    }
}
