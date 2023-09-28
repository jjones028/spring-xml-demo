package io.spektacle.springxmldemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.MessageChannels

@Configuration
class IntegrationChannelConfig {

    @Bean
    fun xml() = MessageChannels.direct().`object`

    @Bean
    fun errors() =MessageChannels.direct().`object`

}