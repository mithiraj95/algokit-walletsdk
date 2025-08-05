package com.michaeltchuang.walletsdk.runtimeaware.deeplink.di

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.DeeplinkHandler
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder.KeyRegTransactionDeepLinkBuilder
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder.MnemonicDeepLinkBuilder
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.CreateDeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.CreateDeepLinkImpl
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.ParseDeepLinkPayload
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.ParseDeepLinkPayloadImpl
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.PeraUriParser
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.PeraUriParserImpl
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.query.MnemonicQueryParser
import org.koin.dsl.module

val deepLinkModule = module {

    // Provide PeraUriParser
    single<PeraUriParser> { PeraUriParserImpl() }

    // Provide ParseDeepLinkPayload
    single<ParseDeepLinkPayload> {
        ParseDeepLinkPayloadImpl(
            peraUriParser = get(),
            mnemonicQueryParser = MnemonicQueryParser(get()),
        )
    }

    // Provide CreateDeepLink
    single<CreateDeepLink> {
        CreateDeepLinkImpl(
            parseDeepLinkPayload = get(),
            mnemonicDeepLinkBuilder = MnemonicDeepLinkBuilder(),
            keyRegTransactionDeepLinkBuilder = KeyRegTransactionDeepLinkBuilder()
        )
    }

    single { DeeplinkHandler(get()) }
}
