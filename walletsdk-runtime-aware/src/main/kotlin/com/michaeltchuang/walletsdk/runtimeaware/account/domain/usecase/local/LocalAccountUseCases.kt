package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountRegistrationType
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount


internal fun interface SaveAlgo25Account {
    suspend operator fun invoke(account: LocalAccount.Algo25, privateKey: ByteArray)
}

internal fun interface DeleteAlgo25Account {
    suspend operator fun invoke(address: String)
}

internal fun interface DeleteHdKeyAccount {
    suspend operator fun invoke(address: String)
}

fun interface GetLocalAccounts {
    suspend operator fun invoke(): List<LocalAccount>
}

internal fun interface SaveHdKeyAccount {
    suspend operator fun invoke(account: LocalAccount.HdKey, privateKey: ByteArray)
}

fun interface GetSeedIdIfExistingEntropy {
    suspend operator fun invoke(entropy: ByteArray): Int?
}

interface GetAccountRegistrationType {
    suspend operator fun invoke(address: String): AccountRegistrationType?
    operator fun invoke(account: LocalAccount): AccountRegistrationType
}

