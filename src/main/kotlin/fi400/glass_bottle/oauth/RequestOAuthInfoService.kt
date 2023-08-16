package fi400.glass_bottle.oauth

import org.springframework.stereotype.Component

@Component
class RequestOAuthInfoService(clients: List<OAuthApiClient>) {
    private val clients: Map<OAuthProvider, OAuthApiClient> = clients.associateBy { it.oAuthProvider() }

    fun request(params: OAuthLoginParams): OAuthInfoResponse {
        val client = clients[params.oAuthProvider()] ?: throw IllegalStateException("Client not found");
        val accessToken = client.requestAccessToken(params)
        return client.requestOauthInfo(accessToken)
    }
}