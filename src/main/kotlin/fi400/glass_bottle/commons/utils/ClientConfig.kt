package fi400.glass_bottle.commons.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}