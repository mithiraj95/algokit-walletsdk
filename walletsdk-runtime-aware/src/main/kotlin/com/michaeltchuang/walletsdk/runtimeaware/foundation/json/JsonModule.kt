package com.michaeltchuang.walletsdk.runtimeaware.foundation.json

import org.koin.dsl.module

val jsonModule = module {
    single<JsonSerializer> { JsonSerializerImpl(get()) }
}

