package io.spektacle.springxmldemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.file.dsl.Files
import org.springframework.integration.kafka.dsl.Kafka
import org.springframework.integration.sftp.dsl.Sftp
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileReader
import javax.xml.namespace.QName
import javax.xml.stream.XMLEventReader
import javax.xml.stream.XMLEventWriter
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.events.StartElement
import javax.xml.stream.events.XMLEvent


@Configuration
class XmlFileIntegrationConfig(
    private val channels: IntegrationChannelConfig,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val sftpSessionFactory: DefaultSftpSessionFactory
) {

    private val input = File("./input")

    @Bean
    fun fileFlow(): IntegrationFlow = integrationFlow(
        Files.inboundAdapter(input).autoCreateDirectory(true),
        { poller { it.fixedRate(60 * 1000).maxMessagesPerPoll(10) } }
    ) {
        filter<File> { it.isFile }
        route<File> {
            when(it.extension.lowercase()) {
                "xml" -> channels.xml()
                else -> channels.errors()
            }
        }
    }

    @Bean
    fun sftpInboundFlow(): IntegrationFlow = integrationFlow(
        Sftp.inboundAdapter(sftpSessionFactory)
            .filter(SftpSimplePatternFileListFilter("*.xml"))
            .remoteDirectory("/pos/346/exports")
            .localDirectory(File("output")),
        { poller { it.fixedRate(60 * 1000).maxMessagesPerPoll(10) } }
    ) {
        filter<File> { it.isFile }
        route<File> {
            when(it.extension.lowercase()) {
                "xml" -> channels.xml()
                else -> channels.errors()
            }
        }
    }

    @Bean
    fun xmlKafkaFlo(): IntegrationFlow = integrationFlow(channels.event()) {

        handle(
            Kafka
            .outboundChannelAdapter(kafkaTemplate)
            .topic("business-day-events")
            .messageKey { message: Message<ByteArray> -> (message.headers.timestamp.toString()) }
        )
    }

    @Bean
    fun xmlEventFlo(): IntegrationFlow = integrationFlow(channels.xml()) {
        handle {
            val inputFactory = XMLInputFactory.newInstance()
            val reader = inputFactory.createXMLEventReader(FileReader(it.payload as File))
            var storeId : Int? = null
            while (true) {
                val event = reader.nextEvent()
                if (event.isStartElement) {
                    val element = event.asStartElement()
                    when(element.name.localPart) {
                        "StoreLocationID" -> {
                            val storeIdEvent = reader.nextEvent()
                            if (storeIdEvent.isCharacters) storeId = storeIdEvent.asCharacters().data.toInt()
                        }
                        "SaleEvent" -> {
                            val stream = ByteArrayOutputStream()
                            writeToStream(reader, event, stream)
                            val message = MessageBuilder
                                .withPayload(stream.toByteArray())
                                .setHeader(KafkaHeaders.PARTITION, storeId)
                                .build()
                            channels.event().send(message)
                        }
                        "OtherEvent" -> {
                            val stream = ByteArrayOutputStream()
                            writeToStream(reader, event, stream)
                            val message = MessageBuilder
                                .withPayload(stream.toByteArray())
                                .setHeader(KafkaHeaders.PARTITION, storeId)
                                .build()
                            channels.event().send(message)
                        }
                        "FinancialEvent" -> {
                            val stream = ByteArrayOutputStream()
                            writeToStream(reader, event, stream)
                            val message = MessageBuilder
                                .withPayload(stream.toByteArray())
                                .setHeader(KafkaHeaders.PARTITION, storeId)
                                .build()
                            channels.event().send(message)
                        }
                    }
                }
                if (event.isEndDocument) break
            }
            reader.close()
        }
    }

    @Bean
    fun errorsFlow(): IntegrationFlow = integrationFlow(channels.errors()) {
        handle { println(it.payload.toString()) }
    }

    fun writeToStream(reader: XMLEventReader, startEvent: XMLEvent, stream: ByteArrayOutputStream): XMLEventWriter {

        val outputFactory = XMLOutputFactory.newInstance()
        val element: StartElement = startEvent.asStartElement()
        val name: QName = element.name
        var stack = 1
        val writer: XMLEventWriter = outputFactory.createXMLEventWriter(stream)
        writer.add(element)
        while (true) {
            val event = reader.nextEvent()
            if (event.isStartElement && event.asStartElement().name == name) stack++
            if (event.isEndElement) {
                val end = event.asEndElement()
                if (end.name == name) {
                    stack--
                    if (stack == 0) {
                        writer.add(event)
                        break
                    }
                }
            }
            writer.add(event)
        }
        writer.close()
        return writer
    }
}