package io.spektacle.springxmldemo.serializers

import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory
import java.io.File

class FileSerializer : Serializer<File> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: File?): ByteArray? {
        log.info("Serializing...")
        return data?.readBytes()
    }

    override fun close() {}

}
