package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.usecase.GetAlgo25Account
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
