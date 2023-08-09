package fi400.glass_bottle.oauth

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

/**
 * Kakaologinparams
 * 카카오 API 요청에 필요한 authorizationCode 를 갖고 있는 클래스
 * @property authorizationCode 카카오 인증키
 *
 */
data class KakaoLoginParams(
    var authorizationCode: String? = null
) : OAuthLoginParams {

    /**
     * Oauthprovider
     * 카카오 소셜 로그인
     * @return KAKAO : String
     */
    override fun oAuthProvider(): OAuthProvider {
        return OAuthProvider.KAKAO
    }

    /**
     * Make body
     *
     * @return body LinkedMultiValueMap
     */
    override fun makeBody(): MultiValueMap<String, String> {
        val body = LinkedMultiValueMap<String, String>()
        body.add("code", authorizationCode)
        return body
    }
}