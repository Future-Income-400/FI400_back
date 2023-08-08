package fi400.glass_bottle.repository

import fi400.glass_bottle.GlassBottleApplicationTests
import fi400.glass_bottle.model.OAuthProvider
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class RepositoryTest(
        @Autowired val userRepository: UserRepository
)  {

    @Test
    @DisplayName("회원저장성공")
    fun saveUser () {
        val user = User(5, "TEST", "TEST", "test@gmail.com", 0, OAuthProvider.KAKAO)
        val saveUser = userRepository.save(user)

        assertEquals(saveUser.id, user.id)
    }
}