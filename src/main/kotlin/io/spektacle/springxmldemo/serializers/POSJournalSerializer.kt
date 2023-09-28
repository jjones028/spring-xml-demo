package io.spektacle.springxmldemo.serializers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.spektacle.springxmldemo.models.POSJournal
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory
import java.io.File

class POSJournalSerializer : Serializer<File> {

    private val xmlMapper = XmlMapper
        .builder()
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .defaultUseWrapper(false)
        .build()
        .registerModules(JavaTimeModule())
        .registerKotlinModule()

    private val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: File?): ByteArray? {
        log.info("Serializing...")
        val posJournal = xmlMapper.readValue(data,POSJournal::class.java)
        return objectMapper.writeValueAsBytes(
            posJournal ?: throw SerializationException("Error while serializing POSJournal to ByteArray[]")
        )
    }

    override fun close() {}

}
