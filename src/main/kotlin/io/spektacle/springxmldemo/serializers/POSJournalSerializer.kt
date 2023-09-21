package io.spektacle.springxmldemo.serializers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.spektacle.springxmldemo.models.POSJournal
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class POSJournalSerializer : Serializer<POSJournal> {

    private val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: POSJournal?): ByteArray? {
        log.info("Serializing...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error while serializing POSJournal to ByteArray[]")
        )
    }

    override fun close() {}

}
