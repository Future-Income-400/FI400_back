package fi400.glass_bottle.oauth

data class AuthTokens(
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var grantType: String? = null,
    var expiresIn: Long? = null
) {
    companion object {
        fun of(accessToken: String, refreshToken: String, grantType: String, expiresIn: Long): AuthTokens {
            return AuthTokens(accessToken, refreshToken, grantType, expiresIn)
        }
    }
}