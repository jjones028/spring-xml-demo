package io.spektacle.springxmldemo

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.spektacle.springxmldemo.models.POSJournal
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils


@Component
class CommandLineAppStartupRunner : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        logger.info(
            "Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.",
            args.contentToString()
        )

        val xmlDeserializer = XmlMapper
            .builder()

            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .defaultUseWrapper(false)
            .build()
            .registerModules(JavaTimeModule())
            .registerKotlinModule()

        val posJournal = xmlDeserializer.readValue(
            ResourceUtils.getFile("classpath:NAXML-POSJournal_2023-08-09_2023-08-10T06.16.00.086_1of1.xml"),
            POSJournal::class.java
        )
        println(posJournal)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CommandLineAppStartupRunner::class.java)
    }
}