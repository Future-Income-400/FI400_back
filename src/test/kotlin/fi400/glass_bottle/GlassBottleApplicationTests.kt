package fi400.glass_bottle

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GlassBottleApplicationTests {

    @Test
    fun contextLoads() {
        println("Hello World!")
        print("TEST")
        print("TEST")
        print("TEST")
    }

    @Test
    fun testForHello(){
        println("Hello!")
    }

    @Test
    fun testGitPush(){
        println("Push")
    }

}
