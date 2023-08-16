package fi400.glass_bottle.oauth
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Kakaoinforesponse
 * https://kapi.kakao.com/v2/user/me 요청 결과값
 * @property kakaoAccount
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoInfoResponse(
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount
) : OAuthInfoResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoAccount(
        val profile: KakaoProfile,
        val email: String
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class KakaoProfile(
        val name: String
    )

    override val email: String
        get() = kakaoAccount.email

    override val name: String
        get() = kakaoAccount.profile.name

    override val oAuthProvider: OAuthProvider
        get() = OAuthProvider.KAKAO
}
