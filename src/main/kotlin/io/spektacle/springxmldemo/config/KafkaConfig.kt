package io.spektacle.springxmldemo.config

import io.spektacle.springxmldemo.serializers.POSJournalSerializer
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfig {

    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Bean
    fun kafkaAdmin() : KafkaAdmin {
        val configs: MutableMap<String, Any> = mutableMapOf()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return KafkaAdmin(configs)
    }

    @Bean
    fun businessDay(): NewTopic = NewTopic("business-day", 1, 1.toShort())
}

@Configuration
class KafkaProducerConfig(
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private val servers: String
){
    @Bean
    fun producerFactory(): ProducerFactory<String, Any> = DefaultKafkaProducerFactory(mutableMapOf<String, Any>(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to servers,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to POSJournalSerializer::class.java
    ))

    @Bean
    fun kafkaTemplate() = KafkaTemplate(producerFactory())
}