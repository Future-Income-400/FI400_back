package fi400.glass_bottle.oauth

import org.springframework.stereotype.Component
import java.util.Date

@Component
class AuthTokensGenerator(
    private val jwtTokenProvider: JwtTokenProvider
) {
    companion object {
        private const val BEARER_TYPE = "Bearer"
        private const val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30            // 30분
        private const val REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7  // 7일
    }

    fun generate(memberId: Long): AuthTokens {
        val now = Date().time
        val accessTokenExpiredAt = Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        val refreshTokenExpiredAt = Date(now + REFRESH_TOKEN_EXPIRE_TIME)

        val subject = memberId.toString()
        val accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt)
        val refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt)

        return AuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L)
    }

    fun extractMemberId(accessToken: String): Long {
        return jwtTokenProvider.extractSubject(accessToken).toLong()
    }
}