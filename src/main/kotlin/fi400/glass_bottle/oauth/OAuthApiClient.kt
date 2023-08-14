package fi400.glass_bottle.oauth

/**
 * Oauthapiclient
 * OAuth 요청을 위한 Client 클래스
 */
interface OAuthApiClient {

    // Client 의 타입 반환
    fun oAuthProvider(): OAuthProvider

    // Authorization Code 를 기반으로 인증 API 를 요청해서 Access Token 을 획득
    fun requestAccessToken(params: OAuthLoginParams): String

    //Access Token 을 기반으로 Email, Nickname 이 포함된 프로필 정보를 획득
    fun requestOauthInfo(accessToken: String): OAuthInfoResponse
}