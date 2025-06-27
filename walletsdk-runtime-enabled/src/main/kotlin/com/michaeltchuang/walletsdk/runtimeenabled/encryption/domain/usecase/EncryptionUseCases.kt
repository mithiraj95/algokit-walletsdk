package com.michaeltchuang.walletsdk.runtimeenabled.encryption.domain.usecase

import javax.crypto.SecretKey

fun interface GetEncryptionSecretKey {
    operator fun invoke(): SecretKey
}
