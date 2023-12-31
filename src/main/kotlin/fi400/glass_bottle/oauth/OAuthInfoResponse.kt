package fi400.glass_bottle.oauth

/**
 * Oauthinforesponse
 * Access Token 으로 요청한 외부 API 프로필 응답값을 우리 서비스의 Model 로 변환시키기 위한 인터페이스
 */
interface OAuthInfoResponse {
    val email: String
    val nickname: String
    val oAuthProvider: OAuthProvider
}