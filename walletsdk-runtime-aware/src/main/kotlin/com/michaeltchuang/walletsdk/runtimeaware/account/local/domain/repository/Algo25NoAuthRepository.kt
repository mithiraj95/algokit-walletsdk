package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository

internal interface Algo25NoAuthRepository {
    suspend fun updateInvalidAlgo25AccountsToNoAuth()
}
