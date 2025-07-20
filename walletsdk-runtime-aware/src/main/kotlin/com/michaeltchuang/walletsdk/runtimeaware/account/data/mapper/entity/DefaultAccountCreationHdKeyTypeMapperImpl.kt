/*
 * Copyright 2022-2025 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddress


internal class DefaultAccountCreationHdKeyTypeMapperImpl(
    private val aesPlatformManager: AESPlatformManager
) : AccountCreationHdKeyTypeMapper {

    override fun invoke(
        entropy: ByteArray,
        hdKeyAddress: HdKeyAddress,
        seedId: Int?
    ): AccountCreation.Type.HdKey {
        return with(hdKeyAddress) {
            AccountCreation.Type.HdKey(
                publicKey.toByteArray(),
                aesPlatformManager.encryptByteArray(privateKey.toByteArray()),
                aesPlatformManager.encryptByteArray(entropy),
                index.accountIndex,
                index.changeIndex,
                index.keyIndex,
                9,
                seedId
            )
        }
    }
}
