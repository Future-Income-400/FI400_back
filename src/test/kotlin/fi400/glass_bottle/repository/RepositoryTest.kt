package fi400.glass_bottle.repository

import fi400.glass_bottle.GlassBottleApplicationTests
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.oauth.OAuthProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

/**
 * 레포지토리 테스트 클래스
 */
@SpringBootTest
class RepositoryTest(
        @Autowired val userRepository: UserRepository
)  {

    /**
     * 테스트 유저 DB저장 후 비교 테스트
     */
    @Test
    @DisplayName("회원저장성공")
    fun saveUser () {
        val user = User(5, "TEST", "TEST", "test@gmail.com", 0, OAuthProvider.KAKAO)
        val saveUser = userRepository.save(user)

        assertEquals(saveUser.id, user.id)
    }
}