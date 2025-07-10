package com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core

enum class AssetStatus {
    PENDING_FOR_REMOVAL,
    PENDING_FOR_ADDITION,
    OWNED_BY_ACCOUNT;

    companion object {
        fun isPending(status: AssetStatus): Boolean {
            return status != OWNED_BY_ACCOUNT
        }
    }
}
