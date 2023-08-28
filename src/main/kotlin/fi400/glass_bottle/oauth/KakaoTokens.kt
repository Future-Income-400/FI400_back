import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Kakao tokens
 * Authorization Code 를 기반으로 타플랫폼 Access Token 을 받아오기 위한 Response Model
 * AccessToken 만 받아옴
 * @property accessToken
 * @property tokenType
 * @property refreshToken
 * @property expiresIn
 * @property refreshTokenExpiresIn
 * @property scope
 */
data class KakaoTokens(
    @JsonProperty("access_token")
    var accessToken: String? = null,

    @JsonProperty("token_type")
    var tokenType: String? = null,

    @JsonProperty("refresh_token")
    var refreshToken: String? = null,

    @JsonProperty("expires_in")
    var expiresIn: String? = null,

    @JsonProperty("refresh_token_expires_in")
    var refreshTokenExpiresIn: String? = null,

    @JsonProperty("scope")
    var scope: String? = null
)