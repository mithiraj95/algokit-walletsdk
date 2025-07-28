package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder


import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.NotificationGroupType
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.utils.AssetConstants.ALGO_ID
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.utils.getSafeAssetIdForResponse

internal class NotificationDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            accountAddress != null &&
                    assetId != null &&
                    notificationGroupType != null &&
                    amount == null &&
                    walletConnectUrl == null &&
                    url == null &&
                    note == null &&
                    xnote == null &&
                    label == null &&
                    webImportQrCode == null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.Notification(
            address = payload.accountAddress.orEmpty(),
            assetId = getSafeAssetIdForResponse(payload.assetId) ?: ALGO_ID,
            notificationGroupType = payload.notificationGroupType
                ?: NotificationGroupType.TRANSACTIONS
        )
    }
}
