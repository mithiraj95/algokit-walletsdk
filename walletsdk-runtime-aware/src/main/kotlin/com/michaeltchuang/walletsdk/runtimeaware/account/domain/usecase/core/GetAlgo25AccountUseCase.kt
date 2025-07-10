package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class GetAlgo25AccountUseCase(
    private val algo25AccountRepository: Algo25AccountRepository,
    private val dispatcher: CoroutineDispatcher
) : GetAlgo25Account {

    override suspend fun invoke(): List<LocalAccount.Algo25> {
        return withContext(dispatcher) {
            val deferredAlgo25Accounts = async { algo25AccountRepository.getAll() }
            awaitAll(
                deferredAlgo25Accounts
            ).flatten()
        }
    }
}
