package io.spektacle.springxmldemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.file.dsl.Files
import org.springframework.integration.kafka.dsl.Kafka
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.Message
import java.io.File


@Configuration
class XmlFileIntegrationConfig(
    private val channels: IntegrationChannelConfig,
    private val kafkaTemplate: KafkaTemplate<String, Any>
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
    fun xmlKafkaFlo(): IntegrationFlow = integrationFlow(channels.xml()) {

        handle(Kafka
            .outboundChannelAdapter(kafkaTemplate)
            .topic("business-day")
            .messageKey { message: Message<File> -> (message.payload.name) }
        )
    }

    @Bean
    fun errorsFlow(): IntegrationFlow = integrationFlow(channels.errors()) {
        handle { println(it.payload.toString()) }
    }
}