package fi400.glass_bottle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class GlassBottleApplication

fun main(args: Array<String>) {
    runApplication<GlassBottleApplication>(*args)
}
