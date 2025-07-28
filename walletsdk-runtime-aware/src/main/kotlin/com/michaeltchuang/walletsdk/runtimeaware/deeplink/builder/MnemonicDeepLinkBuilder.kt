package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal class MnemonicDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            mnemonic != null &&
                    accountAddress == null &&
                    assetId == null &&
                    amount == null &&
                    walletConnectUrl == null &&
                    url == null &&
                    note == null &&
                    xnote == null &&
                    label == null &&
                    webImportQrCode == null &&
                    notificationGroupType == null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.Mnemonic(payload.mnemonic.orEmpty())
    }
}
