package fi400.glass_bottle.oauth

import org.springframework.util.MultiValueMap

/**
 * Oauthloginparams
 * 소셜 로그인 인터페이스
 * @since 2023.08.07
 */
interface OAuthLoginParams {
    fun oAuthProvider(): OAuthProvider
    fun makeBody(): MultiValueMap<String, String>
}