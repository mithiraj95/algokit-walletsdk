package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal class WalletConnectConnectionDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            walletConnectUrl != null &&
                    accountAddress == null &&
                    assetId == null &&
                    amount == null &&
                    note == null &&
                    url == null &&
                    xnote == null &&
                    label == null &&
                    webImportQrCode == null &&
                    notificationGroupType == null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.WalletConnectConnection(uri = payload.walletConnectUrl.orEmpty())
    }
}
