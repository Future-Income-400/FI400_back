package fi400.glass_bottle.oauth

import KakaoTokens
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

/**
 * Kakaoapiclient
 * 카카오 사용자의 사용자 정보를 가져오기 위한 클래스
 * @property restTemplate
 * @constructor Create empty Kakao api client
 */
@Component
class KakaoApiClient(
    private val restTemplate: RestTemplate
) : OAuthApiClient {

    companion object {
        private const val GRANT_TYPE = "authorization_code"
    }

    @Value("\${oauth.kakao.url.auth}")
    private lateinit var authUrl: String

    @Value("\${oauth.kakao.url.api}")
    private lateinit var apiUrl: String

    @Value("\${oauth.kakao.client-id}")
    private lateinit var clientId: String

//    @Value("\${oauth.kakao.redirect_uri}")
    private val redirectUri: String = "http://localhost:8080/kakao/callback";

    override fun oAuthProvider(): OAuthProvider = OAuthProvider.KAKAO

    override fun requestAccessToken(params: OAuthLoginParams): String {
        print("requestAccessToken called")
        val url = "$authUrl/oauth/token"
        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }
        val body = params.makeBody().apply {
            add("grant_type", GRANT_TYPE)
            add("client_id", clientId)
            add("redirect_uri", redirectUri)
        }

        val request = HttpEntity(body, httpHeaders)
        val response: KakaoTokens? = restTemplate.postForObject(url, request, KakaoTokens::class.java)
        return response?.accessToken ?: throw IllegalStateException("Response is null or missing access token")
    }

    override fun requestOauthInfo(accessToken: String): OAuthInfoResponse {
        print("requestOauthInfo called")
        val url = "$apiUrl/v2/user/me"
        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            set("Authorization", "Bearer $accessToken")
        }
        val body = LinkedMultiValueMap<String, String>().apply {
            add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]")
        }

        val request = HttpEntity(body, httpHeaders)
        return restTemplate.postForObject(url, request, KakaoInfoResponse::class.java)
            ?: throw IllegalStateException("Response is null")
    }
}