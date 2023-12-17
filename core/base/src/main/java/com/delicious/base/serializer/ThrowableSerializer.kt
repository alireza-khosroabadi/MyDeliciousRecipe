package com.delicious.base.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Throwable::class)
object ThrowableSerializer : KSerializer<Throwable> {

    override val descriptor = PrimitiveSerialDescriptor("Throwable", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Throwable) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Throwable {
        return RuntimeException(decoder.decodeString())
    }
}
