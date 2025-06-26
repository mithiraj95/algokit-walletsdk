package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.mapper

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.ApplicationCallStateSchema
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.payload.RawTransactionApplicationCallStateSchemaPayload

internal class ApplicationCallStateSchemaMapperImpl :
    ApplicationCallStateSchemaMapper {

    override fun invoke(payload: RawTransactionApplicationCallStateSchemaPayload?): ApplicationCallStateSchema {
        return ApplicationCallStateSchema(
            numberOfBytes = payload?.numberOfBytes,
            numberOfInts = payload?.numberOfInts
        )
    }
}
