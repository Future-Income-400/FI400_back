package fi400.glass_bottle.oauth

import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class OAuthLoginService(
    private val userRepository: UserRepository,
    private val authTokensGenerator: AuthTokensGenerator,
    private val requestOAuthInfoService: RequestOAuthInfoService
) {

    fun login(params: OAuthLoginParams): AuthTokens {
        val oAuthInfoResponse = requestOAuthInfoService.request(params)
        val userId = findOrCreateMember(oAuthInfoResponse) ?: throw IllegalStateException("User ID cannot be null.")
        return authTokensGenerator.generate(userId)
    }

    private fun findOrCreateMember(oAuthInfoResponse: OAuthInfoResponse): Long? {
        return userRepository.findByEmail(oAuthInfoResponse.email)
            .map { it.id }
            .orElseGet { newMember(oAuthInfoResponse) }

    }

    private fun newMember(oAuthInfoResponse: OAuthInfoResponse): Long {
        val user = User(
            email = oAuthInfoResponse.email,
            nickname = oAuthInfoResponse.nickname,
            isActivated = 'Y',
            oAuthProvider = oAuthInfoResponse.oAuthProvider
        )
        return userRepository.save(user).id ?: throw IllegalStateException("User ID cannot be null.")
    }
}