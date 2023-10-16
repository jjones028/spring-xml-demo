package io.spektacle.springxmldemo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory

@Configuration
class SftpConfig {

    @Value(value = "localhost")
    private lateinit var sftpServer: String

    @Value(value = 2223.toString())
    private lateinit var sftpPort: String

    @Bean
    fun sftpSessionFactory(): DefaultSftpSessionFactory {
        val factory = DefaultSftpSessionFactory()
        factory.setHost(sftpServer)
        factory.setPort(sftpPort.toInt())
        factory.setAllowUnknownKeys(true)
        factory.setUser("admin")
        factory.setPassword("pos")
        return factory
    }

}